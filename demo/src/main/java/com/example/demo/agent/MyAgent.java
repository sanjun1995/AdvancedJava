package com.example.demo.agent;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("MyAgent is running.");
        inst.addTransformer(new MyClassTransformer());
    }
}

