package com.example.demo.b2c.enummapper;

/**
 * @author caozhixin
 * @date 2024/9/18 11:35
 */
// 定义一个枚举，实现 CodeMappable 接口
// 表示产品的不同状态，每个状态对应一个 code 值和描述
enum ProductStatus implements CodeMappable<Integer> {
    NEW(1, "新产品"),
    PROCESSING(2, "处理中"),
    SHIPPED(3, "已发货"),
    DELIVERED(4, "已交付");

    // 枚举的 code 值
    private final Integer code;
    // 枚举的描述
    private final String description;

    // 构造方法，初始化 code 和描述
    ProductStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    // 实现接口方法，返回枚举的 code 值
    @Override
    public Integer code() {
        return this.code;
    }

    // 获取枚举的描述
    public String getDescription() {
        return this.description;
    }
}
