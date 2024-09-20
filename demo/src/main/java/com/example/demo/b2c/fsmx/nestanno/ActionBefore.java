package com.example.demo.b2c.fsmx.nestanno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caozhixin
 * @date 2024/9/20 11:22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionBefore {

    Operate[] value();

    ResultAsParam resultAsParam() default @ResultAsParam(asParam = false);

    int order() default Integer.MAX_VALUE;

    @Target({})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ResultAsParam {
        boolean asParam() default true;
        boolean asChainParam() default false;
    }

    enum Operate {
        OPERATE_1,
        OPERATE_2
    }
}