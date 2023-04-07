package com.example.demo.flow.workflow;

import java.lang.reflect.Method;

/**
 * @author caozhixin
 * @date 2023/4/7 14:48
 */
public class Task {
    private String name;
    private String className;
    private String methodName;

    public Task(String name, String className, String methodName) {
        this.name = name;
        this.className = className;
        this.methodName = methodName;
    }

    public Task() {

    }

    public String getName() {
        return name;
    }

    public void execute() {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod(methodName);

            method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}