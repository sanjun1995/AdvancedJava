//package com.example.demo.bytebuddy;
//
//import com.example.demo.bytebuddy.anno.ClassAnnotation;
//import com.example.demo.bytebuddy.anno.MethodAnnotation;
//import net.bytebuddy.agent.ByteBuddyAgent;
//import net.bytebuddy.agent.builder.AgentBuilder;
//import net.bytebuddy.implementation.MethodDelegation;
//import net.bytebuddy.implementation.SuperMethodCall;
//import net.bytebuddy.matcher.ElementMatchers;
//
//import java.lang.instrument.Instrumentation;
//
//public class Test {
//    public static void main(String[] args) {
//        // 获取Instrumentation实例
//        Instrumentation instrumentation = ByteBuddyAgent.install();
//
//        // 利用Byte Buddy实现方法拦截器
//        new AgentBuilder.Default()
//                .type(ElementMatchers.isAnnotatedWith(ClassAnnotation.class))
//                .transform((builder, typeDescription, classLoader, javaModule, protectionDomain) -> builder
//                        .method(ElementMatchers.isAnnotatedWith(MethodAnnotation.class))
//                        .intercept(MethodDelegation.to(TransmittableThreadLocalInterceptor.class)
//                                .andThen(SuperMethodCall.INSTANCE)))
//                .installOn(instrumentation);
//
//        // 测试
//        ContextHolder.getContext().set("Hello, World!");
//        new AppDemo().printContext();
//    }
//}
//
