package com.example.demo.b2c.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author caozhixin
 * @date 2024/9/20 15:31
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    private final FsmxProperties fsmxProperties;

    @Autowired
    public MyCommandLineRunner(FsmxProperties fsmxProperties) {
        this.fsmxProperties = fsmxProperties;
    }

    @Override
    public void run(String... args) {
        checkFeature(); // 在应用启动后调用 checkFeature 方法
    }

    public void checkFeature() {
        if (fsmxProperties.isEnable()) {
            System.out.println("MyCommandLineRunnerMyCommandLineRunnerMyCommandLineRunner Feature is enabled.");
        } else {
            System.out.println("MyCommandLineRunnerMyCommandLineRunnerMyCommandLineRunner is disabled.");
        }
    }
}
