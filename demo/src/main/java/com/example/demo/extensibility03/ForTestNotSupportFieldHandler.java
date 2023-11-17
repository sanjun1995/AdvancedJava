package com.example.demo.extensibility03;

import java.lang.reflect.Field;

/**
 * @author caozhixin
 * @date 2023/11/17 17:05
 */
public class ForTestNotSupportFieldHandler extends PiiDomainFieldHandlerBase {
    @Override
    public <T> boolean isSupport(T domain, Field domainField) {

        if (this.getClass().getSimpleName().equalsIgnoreCase(domain.getClass().getSimpleName())) {

            // to do business

            System.out.println(this.getClass().getSimpleName() + " is support, to do some business");

            return true;
        }

        return false;
    }

    @Override
    public String getPiiDomainMeta() {
        return this.getClass().getSimpleName();
    }
}
