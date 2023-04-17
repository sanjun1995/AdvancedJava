package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("weChatPay")
public class WeChatPayStrategy implements PaymentStrategy {


    @Override
    public Integer getPayType() {
        return PayTypeEnum.WECHAT_PAY.getCode();
    }

    @Override
    public void pay(double amount) {
        // 微信支付逻辑
        System.out.println("使用微信支付：" + amount + "元");
    }
}