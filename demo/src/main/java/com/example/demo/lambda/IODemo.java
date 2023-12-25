package com.example.demo.lambda;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * @author caozhixin
 * @date 2023/12/22 17:36
 */
public class IODemo {
    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt"))).lines();
        lines.forEach(System.out::println);
    }
}
