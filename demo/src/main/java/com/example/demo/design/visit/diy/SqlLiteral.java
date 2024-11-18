package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 16:58
 */
public class SqlLiteral extends SqlNode {
    private String value;

    public SqlLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T accept(SqlVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
