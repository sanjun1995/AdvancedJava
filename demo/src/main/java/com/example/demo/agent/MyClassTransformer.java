package com.example.demo.agent;

import java.lang.instrument.ClassFileTransformer;

import java.security.ProtectionDomain;
import java.util.Set;

public class MyClassTransformer implements ClassFileTransformer {
    private final Set<String> basePackages;

    public MyClassTransformer(Set<String> basePackages) {
        this.basePackages = basePackages;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (!needEnhance(className)) {
            return classfileBuffer;
        }

        System.out.println("Transforming class: " + className);
        // 进行字节码转换
        // ...
        return classfileBuffer;
    }

    private boolean needEnhance(String className) {
        for (String basePackage : basePackages) {
            if (className.startsWith(basePackage)) {
                return true;
            }
        }
        return false;
    }
}

