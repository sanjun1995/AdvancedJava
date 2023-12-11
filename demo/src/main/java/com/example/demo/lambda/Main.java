//package com.example.demo.lambda;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Consumer;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> list = Arrays.asList("foo", "bar", "baz");
//
//        processListA(list, str -> System.out.println(str.toLowerCase()));
//        processListB(list, str -> System.out.println(str.toUpperCase()));
//    }
//
//    private static void processListA(List<String> list, ListProcessor processor) {
//        for (String str : list) {
//            processor.accept(str);
//        }
//    }
//
//    private static void processListB(List<String> list, ListProcessor processor) {
//        for (String str : list) {
//            processor.accept(str);
//        }
//    }
//
//    interface ListProcessor extends Consumer<String> {}
//}
