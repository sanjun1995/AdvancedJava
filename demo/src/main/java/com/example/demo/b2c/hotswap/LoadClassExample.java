package com.example.demo.b2c.hotswap;

/**
 * @author caozhixin
 * @date 2024/9/16 09:39
 */
public class LoadClassExample {
    public static void main(String[] args) {
        try {
            // 使用系统类加载器加载一个类
            Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("java.util.ArrayList");
            System.out.println("Class loaded: " + clazz.getName());

            // 通过加载器加载自定义类
            Class<?> customClass = ClassLoader.getSystemClassLoader().loadClass("com.example.demo.b2c.hotswap.MyClass");
            System.out.println("Custom class loaded: " + customClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

