package com.example.demo.b2c.fsmx.nestanno;

/**
 * @author caozhixin
 * @date 2024/9/20 11:26
 */
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class AnnotationScanner {

    public static void main(String[] args) {
        // 指定要扫描的包名
        String packageName = "com.example.demo.b2c.fsmx.nestanno"; // 替换为你的包名
        scanAnnotations(packageName);
    }

    public static void scanAnnotations(String packageName) {
        Reflections reflections = new Reflections(packageName);
        // 获取指定包下所有包含注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Action.class);

        for (Class<?> clazz : annotatedClasses) {
            processAnnotations(clazz);
        }
    }

    public static void processAnnotations(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ActionBefore.class)) {
                ActionBefore actionBefore = method.getAnnotation(ActionBefore.class);
                System.out.println("类: " + clazz.getName());
                System.out.println("方法: " + method.getName());
                System.out.println("操作: ");
                for (ActionBefore.Operate operation : actionBefore.value()) {
                    System.out.println("  - " + operation);
                }
                System.out.println("结果作为参数: " + actionBefore.resultAsParam().asChainParam());
                System.out.println("顺序: " + actionBefore.order());
                System.out.println();
            }
        }
    }
}

