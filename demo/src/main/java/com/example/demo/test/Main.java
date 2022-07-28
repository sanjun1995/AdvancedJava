package com.example.demo.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author caozhixin
 * @date 2022/7/29 12:11 AM
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        while (true) {
            try {
                run();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }
    }

    public static void run() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CustomClassLoader customClassLoader = new CustomClassLoader("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes"
        , new String[]{"com.example.demo.test.Foo"});
        Class<?> clazz = customClassLoader.loadClass("com.example.demo.test.Foo");
        Object foo = clazz.newInstance();
        Method method = foo.getClass().getMethod("sayHello", new Class[]{});
        method.invoke(foo, new Object[]{});
    }
}
