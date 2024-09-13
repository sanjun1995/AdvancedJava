package com.example.demo.b2c.validator;

/**
 * @author caozhixin
 * @date 2024/9/13 13:16
 */
public class ValidateDemo {
    public static void main(String[] args) {
        SimpleQueryDTO queryDTO = new SimpleQueryDTO();
        // 设置测试数据
        queryDTO.setPageNum(null); // 这是一个无效值，用于测试验证逻辑
        queryDTO.setPageSize(500);

        // 创建验证器实例
        SimpleValidator validator = new SimpleValidator();

        try {
            // 执行验证
            queryDTO = null;
            validator.validate(queryDTO);
            System.out.println("Validation passed: " + queryDTO.getPageNum() + ", " + queryDTO.getPageSize());
        } catch (RuntimeException e) {
            // 捕获并打印验证异常
            System.err.println("Validation error: " + e.getMessage());
        }
    }
}

