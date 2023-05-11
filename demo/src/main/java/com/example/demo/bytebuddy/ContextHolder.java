package com.example.demo.bytebuddy;

import com.alibaba.ttl.TransmittableThreadLocal;

public class ContextHolder {
    private static final TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<String> getContext() {
        return context;
    }
}

