package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 16:52
 */
abstract class SqlNode {
    public abstract <T> T accept(SqlVisitor<T> visitor);
}
