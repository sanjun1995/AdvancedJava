package com.example.demo.bytebuddy;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

public class TransmittableThreadLocalInterceptor {
    @Advice.OnMethodEnter
    public static void enter() {
        String value = ContextHolder.getContext().get();
        System.out.println("TransmittableThreadLocalInterceptor: enter, context = " + value);
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object result) {
        String value = ContextHolder.getContext().get();
        System.out.println("TransmittableThreadLocalInterceptor: exit, context = " + value);
    }
}
