package com.example.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/12/21 20:17
 */
public class SeqDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Seq<Integer> seq = list::forEach;

        seq.consume(System.out::println);

        // 使用unit方法创建包含整数的Seq实例
//        Seq<Integer> seq = unit(42);

        // 对Seq实例进行操作
//        seq.consume(System.out::println); // 打印输出：42
//        seq.consume(num -> System.out.println("Squared: " + (num * num))); // 打印输出：Squared: 1764
    }


    static <T> Seq<T> unit(T t) {
        return c -> c.accept(t);
    }
}
