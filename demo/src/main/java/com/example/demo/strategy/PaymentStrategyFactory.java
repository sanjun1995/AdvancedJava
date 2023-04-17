package com.example.demo.strategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStrategyFactory implements InitializingBean {
    @Autowired
    private List<PaymentStrategy> paymentStrategies;

    Map<Integer, PaymentStrategy> paymentStrategyMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for (PaymentStrategy paymentStrategy : paymentStrategies) {
            paymentStrategyMap.put(paymentStrategy.getPayType(), paymentStrategy);

        }
    }

    public PaymentStrategy getPaymentStrategy(Integer payType) {
        return paymentStrategyMap.get(payType);
    }
}

