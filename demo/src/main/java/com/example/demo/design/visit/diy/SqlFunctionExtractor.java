package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 17:01
 */
public class SqlFunctionExtractor implements SqlVisitor<Void> {
    @Override
    public Void visit(SqlSelect select) {
        if (select.getFrom() != null) {
            select.getFrom().accept(this);
        }
        if (select.getWhere() != null) {
            select.getWhere().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(SqlIdentifier identifier) {
        // Do nothing for identifiers
        return null;
    }

    @Override
    public Void visit(SqlLiteral literal) {
        // Do nothing for literals
        return null;
    }

    public void extract(SqlNode sqlNode) {
        sqlNode.accept(this);
    }
}
