package com.example.demo.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.demo.strategy")
public class AppConfig {
    @Bean("aliPay")
    public PaymentStrategy aliPayStrategy() {
        return new AliPayStrategy();
    }

    @Bean("weChatPay")
    public PaymentStrategy weChatPayStrategy() {
        return new WeChatPayStrategy();
    }
}

