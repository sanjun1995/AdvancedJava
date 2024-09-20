package com.example.demo.b2c.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author caozhixin
 * @date 2024/9/20 14:37
 */
@Configuration
@ConditionalOnProperty(prefix = "app.feature", name = "enabled", havingValue = "true")
public class MyAutoConfiguration {

//    @Bean
//    public MyService myService() {
//        return new MyService();
//    }
}
