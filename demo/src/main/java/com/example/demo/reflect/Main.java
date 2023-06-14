package com.example.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author caozhixin
 * @date 2023/6/14 22:49
 */
public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        ReflectClass reflectClass = ReflectClass.class.getDeclaredConstructor().newInstance();

        Field field = ReflectClass.class.getDeclaredField("field01");
        field.setAccessible(true);
        field.set(reflectClass, "value01");

        Method method = ReflectClass.class.getMethod("getField01");
        System.out.println((String) method.invoke(reflectClass));
    }
}
