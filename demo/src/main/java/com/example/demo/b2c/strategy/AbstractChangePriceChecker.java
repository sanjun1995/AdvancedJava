package com.example.demo.b2c.strategy;

/**
 * @author caozhixin
 * @date 2024/9/13 09:28
 */
public abstract class AbstractChangePriceChecker implements ChangePriceChecker {
    @Override
    public void check(SupplyProduct product, Integer price) {
        // 通用检查逻辑
        System.out.println("Checking price for product: " + product.getName() + ", price: " + price);

        // 交给具体策略实现的检查逻辑
        nextCheck(product, price);
    }

    protected abstract void nextCheck(SupplyProduct product, Integer price);
}

