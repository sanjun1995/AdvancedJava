package com.example.demo.agent;

import java.lang.instrument.Instrumentation;
import java.util.Set;

public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        // 获取需要扫描的包名
        Set<String> basePackages = ConfigService.getBasePackages();

        // 构造 MyClassTransformer
        MyClassTransformer transformer = new MyClassTransformer(basePackages);

        inst.addTransformer(transformer);
    }
}

