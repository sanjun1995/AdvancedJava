package com.example.demo.agent;

import java.lang.instrument.ClassFileTransformer;

import java.security.ProtectionDomain;

public class MyClassTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("Transforming class: " + className);
        return classfileBuffer;
    }
}

