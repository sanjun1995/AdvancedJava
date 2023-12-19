package com.example.demo.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/12/19 16:20
 */
public class GenericClassDemo {
    public static void main(String[] args) {
        System.out.println("demo" instanceof Object); // true
        System.out.println(new String() instanceof String); // true
        System.out.println(new Object() instanceof String); // false
//        System.out.println('A' instanceof Character);
        System.out.println(null instanceof String); // false
        System.out.println((String) null instanceof String); // false
//        System.out.println(new Date() instanceof String);
//        System.out.println("demo" instanceof null);

        String str[] = new String[2];
        System.out.println(str instanceof String[]); // true

        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3);
        System.out.println(list instanceof List<?>); // true


        class GenericClass<T> {
            public boolean isDateInstance(T t) {
                return t instanceof Date;
            }
        }

        System.out.println(new GenericClass<String>().isDateInstance("")); // false
    }
}
