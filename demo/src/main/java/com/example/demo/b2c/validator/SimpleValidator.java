package com.example.demo.b2c.validator;

/**
 * @author caozhixin
 * @date 2024/9/13 13:15
 */
public class SimpleValidator extends BaseValidator<SimpleQueryDTO> {

    /**
     * 个性化校验逻辑
     */
    @Override
    public void innerValidate(SimpleQueryDTO queryDTO) {
        if (queryDTO.getPageNum() == null) {
            queryDTO.setPageNum(1); // 默认值
        }

        if (queryDTO.getPageNum() <= 0) {
            throw new RuntimeException("参数pageNum必须为正整数");
        }

        if (queryDTO.getPageSize() == null) {
            queryDTO.setPageSize(10); // 默认值
        }

        if (queryDTO.getPageSize() <= 0 || queryDTO.getPageSize() > 1000) {
            throw new RuntimeException("参数pageSize必须为1-1000之间的正整数");
        }
    }
}

