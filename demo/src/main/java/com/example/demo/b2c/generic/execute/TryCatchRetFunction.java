package com.example.demo.b2c.generic.execute;

/**
 * @author caozhixin
 * @date 2024/9/14 11:12
 */
@FunctionalInterface
public interface TryCatchRetFunction<T> {
    T apply() throws Exception;
}