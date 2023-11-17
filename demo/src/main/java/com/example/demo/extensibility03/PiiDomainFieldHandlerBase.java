package com.example.demo.extensibility03;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author caozhixin
 * @date 2023/11/17 17:03
 */
@Slf4j
public abstract class PiiDomainFieldHandlerBase implements PiiDomainFieldHandler {
    @Override
    public <T extends Object> boolean handlerRead(T domain, Field domainField, PiiContent piiContent) {
        // to do business read

        return true;
    }

    @Override
    public <T extends Object> boolean handlerWrite(T domain, Field domainField, PiiContent piiContent) {

        // to do business write

        return true;
    }
}
