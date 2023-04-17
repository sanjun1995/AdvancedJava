package com.example.demo.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    @Autowired
    private PaymentStrategyFactory paymentStrategyFactory;

    public void payOrder(Integer payType, double amount) {
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(payType);
        paymentStrategy.pay(amount);
    }
}

