package com.example.demo.generics;

/**
 * @author caozhixin
 * @date 2023/6/12 19:19
 */
public class Demo {
    public static <T> Class<T> typeOf(T obj) {
        return (Class<T>)obj.getClass();
    }

    public static void main(String[] args) {
        GoodName<String> goodName = new GoodName<>();
        System.out.println(typeOf(goodName));
    }
}
