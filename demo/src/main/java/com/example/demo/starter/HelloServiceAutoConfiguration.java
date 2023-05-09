package com.example.demo.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloServiceProperties.class)
public class HelloServiceAutoConfiguration {
    private final HelloServiceProperties properties;

    public HelloServiceAutoConfiguration(HelloServiceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.sayHello(properties.getMessage());
        return helloService;
    }
}

