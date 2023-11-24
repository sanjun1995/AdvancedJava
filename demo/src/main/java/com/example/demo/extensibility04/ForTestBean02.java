package com.example.demo.extensibility04;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author caozhixin
 * @date 2023/11/17 20:44
 */
@ForTestAnnotation
public class ForTestBean02 implements InitializingBean {
    public ForTestBean02() {
        System.out.println(ForTestBean02.class.getSimpleName() + " init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean ForTestBean02 init");
    }
}
