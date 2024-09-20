package com.example.demo.b2c.autoconfig;

/**
 * @author caozhixin
 * @date 2024/9/20 15:30
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyAppRunner implements ApplicationRunner {
    private final FsmxProperties fsmxProperties;

    @Autowired
    public MyAppRunner(FsmxProperties fsmxProperties) {
        this.fsmxProperties = fsmxProperties;
    }

    @Override
    public void run(ApplicationArguments args) {
        checkFeature(); // 在应用启动后调用 checkFeature 方法
    }

    public void checkFeature() {
        if (fsmxProperties.isEnable()) {
            System.out.println("MyAppRunnerMyAppRunnerMyAppRunner Feature is enabled.");
        } else {
            System.out.println("MyAppRunnerMyAppRunnerMyAppRunner Feature is disabled.");
        }
    }
}
