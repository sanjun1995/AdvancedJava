package com.example.demo.strategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStrategyFactory implements InitializingBean {
    /**
     * 通过注解直接将所有PaymentStrategy组件注入到list中
     */
    @Autowired
    private List<PaymentStrategy> paymentStrategies;

    Map<Integer, PaymentStrategy> paymentStrategyMap = new HashMap<>();

    /**
     * 构建payType为key的paymentStrategyMap
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (PaymentStrategy paymentStrategy : paymentStrategies) {
            paymentStrategyMap.put(paymentStrategy.getPayType(), paymentStrategy);

        }
    }

    /**
     * 获取具体的实现类
     * @param payType
     * @return
     */
    public PaymentStrategy getPaymentStrategy(Integer payType) {
        return paymentStrategyMap.get(payType);
    }
}

