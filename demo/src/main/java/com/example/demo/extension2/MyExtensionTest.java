package com.example.demo.extension2;

import java.util.ServiceLoader;

public class MyExtensionTest {
    public static void main(String[] args) {
        // 加载扩展点
        ServiceLoader<MyExtension> extensions = ServiceLoader.load(MyExtension.class);

        // 遍历扩展点并执行操作
        for (MyExtension extension : extensions) {
            extension.doSomething();
        }
    }
}

