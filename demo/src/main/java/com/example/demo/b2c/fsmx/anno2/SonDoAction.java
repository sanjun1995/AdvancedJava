package com.example.demo.b2c.fsmx.anno2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caozhixin
 * @date 2024/9/20 17:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@DoAction
public @interface SonDoAction {
    Operate[] value();
}
