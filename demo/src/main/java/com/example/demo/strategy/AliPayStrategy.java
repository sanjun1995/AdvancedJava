package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("aliPay")
public class AliPayStrategy implements PaymentStrategy {
    @Override
    public Integer getPayType() {
        return PayTypeEnum.ALI_PAY.getCode();
    }

    @Override
    public void pay(double amount) {
        // 支付宝支付逻辑
        System.out.println("使用支付宝支付：" + amount + "元");
    }
}