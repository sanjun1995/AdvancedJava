package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 16:57
 */
public class SqlIdentifier extends SqlNode {
    private String name;

    public SqlIdentifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(SqlVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
