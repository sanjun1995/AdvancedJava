package com.example.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author caozhixin
 * @date 2023/12/21 20:17
 */
public class SeqDemo {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3);
//        Seq<Integer> seq = list::forEach;

//        seq.consume(System.out::println);

        // 使用unit方法创建包含整数的Seq实例
//        Seq<Integer> seq = unit(42);
//        Seq<Integer> seq2 = c -> c.accept(43);
//
//        Seq<String> mappedSeq = seq.map(Object::toString);
//        mappedSeq.consume(System.out::println);

        // 对Seq实例进行操作
//        seq.consume(System.out::println); // 打印输出：42
//        seq.consume(num -> System.out.println("Squared: " + (num * num))); // 打印输出：Squared: 1764


//        Seq<String> seq = Seq.of("hello", "world");
//        Seq<String> mappedSeq = seq.flatMap(s -> Seq.of(s, s.toUpperCase()));
//
//        // 调用 consume 方法对 mappedSeq 进行消费
//        mappedSeq.consume(System.out::println);

        Seq<String> seq = Seq.of("apple", "banana", "orange", "grape", "kiwi");


        // 使用 filter 方法过滤元素
        Seq<String> filteredSeq = seq.filter(s -> s.length() >= 6);

        // 调用 consume 方法对过滤后的序列进行消费
        filteredSeq.consume(System.out::println);
    }


    static <T> Seq<T> unit(T t) {
        return c -> c.accept(t);
    }
}
