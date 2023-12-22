package com.example.demo.lambda;

/**
 * @author caozhixin
 * @date 2023/12/22 14:02
 */
public class Seq2Demo {
    public static void main(String[] args) {
        Seq2 seq2 = () -> System.out.println("Consuming element");
        seq2.consume();
    }
}
