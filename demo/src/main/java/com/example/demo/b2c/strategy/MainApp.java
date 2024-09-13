package com.example.demo.b2c.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author caozhixin
 * @date 2024/9/13 09:37
 */
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            AlterPriceCheckerDispatcher dispatcher = context.getBean(AlterPriceCheckerDispatcher.class);

            SupplyProduct product = new SupplyProduct("Test Product", QcLevel.ZL);
            int price = 100;

            dispatcher.check(product, price);
        };
    }
}
