package com.example.demo.b2c.strategy;

/**
 * @author caozhixin
 * @date 2024/9/13 09:29
 */
import org.springframework.stereotype.Component;

@Component
public class AlterZLPriceChecker extends AbstractChangePriceChecker {

    @Override
    public QcLevel getQcLevel() {
        return QcLevel.ZL;
    }

    @Override
    protected void nextCheck(SupplyProduct product, Integer price) {
        System.out.println("ZL Price Checker: Price check logic specific to ZL.");
    }
}

