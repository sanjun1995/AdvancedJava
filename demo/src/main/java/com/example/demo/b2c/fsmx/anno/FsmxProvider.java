package com.example.demo.b2c.fsmx.anno;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caozhixin
 * @date 2024/9/20 10:34
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ModuleProvider(module = "", provideFor = ModuleProvider.ProvideFor.NONE)
@Component
public @interface FsmxProvider {
    @AliasFor(annotation = ModuleProvider.class, attribute = "module")
    String module();

    @AliasFor(annotation = ModuleProvider.class, attribute = "provideFor")
    ModuleProvider.ProvideFor provideFor();
}
