package com.example.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
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

//        Seq<String> seq = Seq.of("apple", "banana", "orange", "grape", "kiwi");
//        // 使用 filter 方法过滤元素
//        Seq<String> filteredSeq = seq.filter(s -> s.length() >= 6);
//        // 调用 consume 方法对过滤后的序列进行消费
//        filteredSeq.consume(System.out::println);

//        Seq<Integer> seq = Seq.of(1, 2, 3, 4, 5);
        // 使用 take 方法限制元素数量为3
//        Seq<Integer> limitedSeq = seq.take(3);
//        Seq<Integer> dropSeq = seq.drop(3);
//        // 调用 consume 方法消费元素
//        dropSeq.consume(System.out::println);

//        // 使用 onEach 方法在每个元素上执行额外的操作
//        Seq<Integer> modifiedSeq = seq.onEach(e -> System.out.println("Processing element: " + e));
//
//        // 调用 consume 方法消费元素
//        modifiedSeq.consume(System.out::println);

//        Seq<Integer> seq = Seq.of(1, 2, 3, 4, 5);
//        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
//
//        Seq<String> zippedSeq = seq.zip(list, (num, str) -> num + ": " + str);
//
//        zippedSeq.consume(System.out::println);

//        Seq<String> words = Seq.of("apple", "banana", "cherry");
//
//        // 使用 join 方法将字符串序列连接成一个以逗号分隔的字符串
//        String joinedString = words.join(", ");
//        System.out.println("Joined String: " + joinedString);
//
//        // 使用 toList 方法将字符串序列转换为 List
//        List<String> wordList = words.toList();
//        System.out.println("List of Words: " + wordList);


        String result = underscoreToCamel("hello_world");
        System.out.println(result);
    }

    static String underscoreToCamel(String str) {
        // Java没有首字母大写方法，随便现写一个
        UnaryOperator<String> capitalize = s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        // 利用生成器构造一个方法的流
        Seq<UnaryOperator<String>> seq = c -> {
            // yield第一个小写函数
//            c.accept(String::toLowerCase);
            // 这里IDEA会告警，提示死循环风险，无视即可
            while (true) {
                // 按需yield首字母大写函数
                c.accept(capitalize);
            }
        };
        List<String> split = Arrays.asList(str.split("_"));
        // 这里的zip和join都在上文给出了实现
        return seq.zip(split, (f, sub) -> f.apply(sub)).join("");
    }

    static <T> Seq<T> unit(T t) {
        return c -> c.accept(t);
    }
}
