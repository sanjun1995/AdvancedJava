package com.example.demo.b2c.strategy;

import java.util.Collections;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2024/9/13 09:27
 */
public interface ChangePriceChecker extends Strategy<QcLevel> {
    @Override
    default Set<QcLevel> strategyCodes() {
        return Collections.singleton(getQcLevel());
    }

    QcLevel getQcLevel();

    void check(SupplyProduct product, Integer price);
}
