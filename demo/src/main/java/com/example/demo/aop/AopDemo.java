package com.example.demo.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class AopDemo {

    public static void main(String[] args) {
        // 创建目标对象
        MyService target = new MyService();

        // 创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 添加前置通知（增强逻辑）
        proxyFactory.addAdvice(new MyMethodBeforeAdvice());

        // 获取代理对象
        MyService proxy = (MyService) proxyFactory.getProxy();

        // 调用代理对象的方法
        proxy.sayHello();
    }

    // 目标类
    static class MyService {
        public void sayHello() {
            System.out.println("Hello, this is the original method.");
        }
    }

    // 前置通知（增强逻辑）
    static class MyMethodBeforeAdvice implements MethodBeforeAdvice {
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("Before method execution: " + method.getName());
        }
    }
}