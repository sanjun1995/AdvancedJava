package com.example.demo.strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);

        orderService.payOrder(PayTypeEnum.ALI_PAY.getCode(), 135.1);
        orderService.payOrder(PayTypeEnum.WECHAT_PAY.getCode(), 112.3);
    }
}
