package com.example.demo.lambda;

import java.util.*;
import java.util.function.*;

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

    static <T> T stop() {
        throw StopException.INSTANCE;
    }

    default void consumeTillStop(Consumer<T> consumer) {
        try {
            consume(consumer);
        } catch (StopException ignore) {}
    }

    default Seq<T> take(int n) {
        return c -> {
            int[] i = {n};
            consumeTillStop(t -> {
                // 遍历n次
                if (i[0]-- > 0) {
                    c.accept(t);
                } else {
                    stop();
                }
            });
        };
    }

    default Seq<T> drop(int n) {
        return c -> {
            int[] a = {n - 1};
            consume(t -> {
                if (a[0] < 0) {
                    c.accept(t);
                } else {
                    a[0]--;
                }
            });
        };
    }

    default Seq<T> onEach(Consumer<T> consumer) {
        return c -> consume(consumer.andThen(c));
    }

    default <E, R> Seq<R> zip(Iterable<E> iterable, BiFunction<T, E, R> function) {
        return c -> {
            Iterator<E> iterator = iterable.iterator();
            consumeTillStop(t -> {
                if (iterator.hasNext()) {
                    c.accept(function.apply(t, iterator.next()));
                } else {
                    stop();
                }
            });
        };
    }

    default String join(String sep) {
        StringJoiner joiner = new StringJoiner(sep);
        consume(t -> joiner.add(t.toString()));
        return joiner.toString();
    }

    default List<T> toList() {
        List<T> list = new ArrayList<>();
        consume(list::add);
        return list;
    }
}
