package com.example.demo.anno;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Properties;

/**
 * @author caozhixin
 * @date 2023/6/14 16:04
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MyClass myObject = new MyClass();

        // 读取配置文件
        Properties properties = new Properties();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        }

        // 获取配置项的值
        String configValue = properties.getProperty("app.config.value");

        // 使用反射修改注解的值
        MyAnnotation annotation = myObject.getClass().getAnnotation(MyAnnotation.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field field;
        try {
            field = invocationHandler.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) field.get(invocationHandler);
            memberValues.put("value", configValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 使用修改后的注解值
        System.out.println(annotation.value()); // 输出: abc
    }
}
