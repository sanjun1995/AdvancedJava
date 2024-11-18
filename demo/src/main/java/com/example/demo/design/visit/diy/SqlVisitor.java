package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 16:53
 */
interface SqlVisitor<T> {
    T visit(SqlSelect select);
    T visit(SqlIdentifier identifier);
    T visit(SqlLiteral literal);
}

