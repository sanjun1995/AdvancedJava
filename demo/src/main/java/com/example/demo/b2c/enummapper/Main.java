package com.example.demo.b2c.enummapper;

/**
 * @author caozhixin
 * @date 2024/9/18 11:36
 */
public class Main {
    public static void main(String[] args) {
        // 通过 code 查找枚举实例
        ProductStatus status = EnumCodeMapper.findByCode(ProductStatus.class, 3);
        System.out.println("找到的 ProductStatus: " + status + " (" + status.getDescription() + ")");

        // 尝试查找一个不存在的 code 值（传递 false，表示找不到时不抛异常）
        ProductStatus unknownStatus = EnumCodeMapper.findByCode(ProductStatus.class, 5, false);
        System.out.println("未知的 ProductStatus: " + unknownStatus);  // 应该输出 'null'
    }
}
