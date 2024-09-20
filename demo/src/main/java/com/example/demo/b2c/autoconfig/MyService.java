package com.example.demo.b2c.autoconfig;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author caozhixin
 * @date 2024/9/20 14:37
 */
@Service
public class MyService {
    @Resource
    FsmxProperties fsmxProperties;

    public MyService() {
        System.out.println("MyService initialized!");
    }

    @PostConstruct
    public void init() {
        checkFeature(); // 在应用启动时调用 checkFeature 方法
    }

    public void checkFeature() {
        if (fsmxProperties.isEnable()) {
            System.out.println("Feature is enabled.");
        } else {
            System.out.println("Feature is disabled.");
        }
    }
}

