package com.example.demo.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        // 创建一个新的ClassLoader
        ClassLoader myClassLoader = new MyClassLoader();

        // 使用应用类加载器加载Test类
        Class<?> clazz = Test.class;

        // 使用线程上下文类加载器加载Test类
        Thread.currentThread().setContextClassLoader(myClassLoader);
        Class<?> clazz2 = Thread.currentThread().getContextClassLoader().loadClass(clazz.getName());

        // 创建Test对象并调用方法
        Test test = new Test();
        System.out.println(test.getClass().getName());
        System.out.println(test.getClass().getClassLoader());
        test.sayHello();

        System.out.println("================================================");

        // 创建Test对象并调用方法
        Object test2 = clazz2.newInstance();
        System.out.println(test2.getClass().getName());
        System.out.println(test2.getClass().getClassLoader());
        // 调用sayHello方法
        Method method = clazz2.getMethod("sayHello");
        method.invoke(test2);
    }

    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals(Test.class.getName())) {
            // 使用自定义的ClassLoader加载Test类
            byte[] bytes = getClassData(name);
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.loadClass(name);
    }

    private byte[] getClassData(String className) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/") + ".class");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            int nextValue = is.read();
            while (nextValue != -1) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load class " + className);
        }
        return byteStream.toByteArray();
    }
}