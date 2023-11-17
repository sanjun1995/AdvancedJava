package com.example.demo.extensibility04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author caozhixin
 * @date 2023/11/17 20:44
 */
public class ForTestClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.demo.extensibility04");
    }
}
