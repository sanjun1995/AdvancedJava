package com.example.demo.loader;

public class Main {

    public static void sayHello() {
        System.err.println("Hello, world! Main");
        System.err.println(Thread.currentThread().getContextClassLoader());
        System.err.println(Main.class.getClassLoader());
        System.err.println(Test.class.getClassLoader());
        Test.sayHello();

//        System.err.println("Hello, world! Main");
//        System.err.println(Thread.currentThread().getContextClassLoader());
//        System.err.println(Test.class.getClassLoader());
//        Test.sayHello();
    }
}
