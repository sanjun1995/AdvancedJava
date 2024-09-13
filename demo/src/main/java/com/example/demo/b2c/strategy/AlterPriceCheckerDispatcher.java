package com.example.demo.b2c.strategy;

/**
 * @author caozhixin
 * @date 2024/9/13 09:36
 */
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AlterPriceCheckerDispatcher extends StrategyAssembler<QcLevel, ChangePriceChecker>
        implements ChangePriceChecker {

    @Override
    public QcLevel getQcLevel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void check(SupplyProduct product, Integer price) {
        QcLevel qcLevel = product.getQcLevel();
        ChangePriceChecker changePriceChecker = strategyInstanceOf(qcLevel);
        changePriceChecker.check(product, price);
    }
}

