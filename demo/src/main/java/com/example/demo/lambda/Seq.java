package com.example.demo.lambda;

import java.util.function.Consumer;

/**
 * @author caozhixin
 * @date 2023/12/21 20:15
 */
public interface Seq<T> {
    void consume(Consumer<T> consumer);
}
