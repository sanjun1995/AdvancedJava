package com.example.demo.extension2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class MyExtensionTest {
    public static void main(String[] args) {
        // 手动加载扩展点
        ServiceLoader<MyExtension> extensions = ServiceLoader.load(MyExtension.class);

        // 遍历扩展点并执行操作
        for (MyExtension extension : extensions) {
            System.out.println(extension.getClass().hashCode());
        }

        // 手动加载扩展点
        extensions = ServiceLoader.load(MyExtension.class);

        // 遍历扩展点并执行操作
        for (MyExtension extension : extensions) {
            System.out.println(extension.getClass().hashCode());
        }
    }

//    public static <T> List<T> loadExtensions(Class<T> extensionType) {
//        List<T> instances = new ArrayList<>();
//        ServiceLoader<T> serviceLoader = ServiceLoader.load(extensionType);
//
//        for (T instance : serviceLoader) {
//            try {
//                // 使用类的 clone() 方法创建新的实例
//                Method cloneMethod = Object.class.getDeclaredMethod("clone");
//                cloneMethod.setAccessible(true);
//                T newInstance = (T) cloneMethod.invoke(instance);
//                instances.add(newInstance);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                // 处理异常
//            }
//        }
//
//        return instances;
//    }


//    public static <T> List<T> loadExtensions(Class<T> extensionType) {
//        List<T> instances = new ArrayList<>();
//        for (T instance : ServiceLoader.load(extensionType)) {
//            instances.add(instance);
//        }
//        return instances;
//    }
}

