package com.example.demo.b2c.hotswap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author caozhixin
 * @date 2024/9/16 09:40
 */

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 这里直接使用绝对路径加载类文件
            String classFilePath = "/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes/com/example/demo/b2c/hotswap/MyClass.class";
            System.out.println("Loading class from: " + classFilePath);

            // 读取字节码文件
            byte[] classData = Files.readAllBytes(Paths.get(classFilePath));

            // 使用 defineClass 将字节数组定义为类
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class not found", e);
        }
    }

    public static void main(String[] args) {
        try {
            CustomClassLoader classLoader = new CustomClassLoader();
            // 使用类的完全限定名来加载
            Class<?> clazz = classLoader.findClass("com.example.demo.b2c.hotswap.MyClass");
            System.out.println("Class loaded using defineClass: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

