package com.example.demo.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author caozhixin
 * @date 2023/12/21 20:15
 */
public interface Seq<T> {
    void consume(Consumer<T> consumer);

//    default <E> Seq<E> map(Function<T, E> function) {
//        return c -> consume(t -> c.accept(function.apply(t)));
//    }
//
//    default <E> Seq<E> flatMap(Function<T, Seq<E>> function) {
//        return c -> consume(t -> function.apply(t).consume(c));
//    }

    // 工厂方法，创建包含给定元素的 Seq 对象
    static <E> Seq<E> of(E... elements) {
        return c -> {
            for (E element : elements) {
                c.accept(element);
            }
        };
    }

    default Seq<T> filter(Predicate<T> predicate) {
        return c -> consume(t -> {
            if (predicate.test(t)) {
                c.accept(t);
            }
        });
    }
}
