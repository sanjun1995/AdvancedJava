package com.example.demo.b2c.validator;

import org.apache.commons.lang3.Validate;
/**
 * @author caozhixin
 * @date 2024/9/13 13:14
 */

public abstract class BaseValidator<Q> {
    /**
     * 验证入口
     */
    public void validate(Q params) {
        try {
            Validate.notNull(params, "params can not be null");
            innerValidate(params);
        } catch (Exception e) {
            throw new RuntimeException("Validation failed: " + e.getMessage(), e);
        }
    }

    /**
     * 个性化校验逻辑
     *
     * @param params 个性化参数
     */
    public abstract void innerValidate(Q params);
}

