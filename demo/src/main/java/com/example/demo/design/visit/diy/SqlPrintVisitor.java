package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 17:00
 */
public class SqlPrintVisitor implements SqlVisitor<String> {
    @Override
    public String visit(SqlSelect select) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if (select.getFrom() != null) {
            sb.append("FROM ").append(select.getFrom().accept(this)).append(" ");
        }
        if (select.getWhere() != null) {
            sb.append("WHERE ").append(select.getWhere().accept(this));
        }
        return sb.toString();
    }

    @Override
    public String visit(SqlIdentifier identifier) {
        return identifier.getName();
    }

    @Override
    public String visit(SqlLiteral literal) {
        return "'" + literal.getValue() + "'";
    }
}
