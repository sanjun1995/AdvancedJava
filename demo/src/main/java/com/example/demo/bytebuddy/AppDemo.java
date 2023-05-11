package com.example.demo.bytebuddy;

import com.example.demo.bytebuddy.anno.ClassAnnotation;
import com.example.demo.bytebuddy.anno.MethodAnnotation;

@ClassAnnotation
public class AppDemo {
    @MethodAnnotation
    public void printContext() {
        String value = ContextHolder.getContext().get();
        System.out.println("AppDemo, context = " + value);
    }
}
