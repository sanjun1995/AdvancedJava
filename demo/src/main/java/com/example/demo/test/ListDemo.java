package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caozhixin
 * @date 2022/9/16 2:26 PM
 */
public class ListDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("123");
        stringList.add("1234");
        System.out.println(stringList.contains("123"));
    }
}
