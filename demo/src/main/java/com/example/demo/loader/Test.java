package com.example.demo.loader;

import java.io.*;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception {
        // 创建一个新的ClassLoader
        ClassLoader myClassLoader = new MyClassLoader();
        // 使用线程上下文类加载器加载Test类
        Thread.currentThread().setContextClassLoader(myClassLoader);
        Class<?> clazz2 = myClassLoader.loadClass("com.example.demo.loader.Main");

        Method method = clazz2.getDeclaredMethod("sayHello");
        method.invoke(null);

//        System.err.println("++++++++++++++");
//        Main.sayHello();

    }

    public static void sayHello() {
        System.err.println("Hello, world! Test");
        System.err.println(Test.class.getClassLoader());
    }
}

class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("com.example.demo.loader.Main")) {
            FileInputStream in;
            try {
                in = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes/com/example/demo/loader/Main.class");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            long fileSize = new File("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes/com/example/demo/loader/Main.class").length();
            byte[] clazzSize = new byte[(int) fileSize];
            try {
                in.read(clazzSize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return defineClass(name, clazzSize, 0, clazzSize.length);
        } else if (name.equals("com.example.demo.loaderTest")) {
            FileInputStream in;
            try {
                in = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes/com/example/demo/loader/Test.class");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            long fileSize = new File("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes/com/example/demo/loader/Test.class").length();
            byte[] clazzSize = new byte[(int) fileSize];
            try {
                in.read(clazzSize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return defineClass(name, clazzSize, 0, clazzSize.length);
        }
        return super.loadClass(name);
    }
}