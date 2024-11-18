package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 16:54
 */
public class SqlSelect extends SqlNode {
    private SqlNode from;
    private SqlNode where;

    public SqlSelect(SqlNode from, SqlNode where) {
        this.from = from;
        this.where = where;
    }

    public SqlNode getFrom() {
        return from;
    }

    public SqlNode getWhere() {
        return where;
    }

    @Override
    public <T> T accept(SqlVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
