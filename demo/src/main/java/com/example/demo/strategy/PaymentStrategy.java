package com.example.demo.strategy;

public interface PaymentStrategy {

    /**
     * 获取支付方式
     * @return
     */
    Integer getPayType();

    /**
     * 进行支付
     * @param amount
     */
    void pay(double amount);
}
