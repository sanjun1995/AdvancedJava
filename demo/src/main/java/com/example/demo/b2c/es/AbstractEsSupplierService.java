package com.example.demo.b2c.es;

import java.util.function.Function;

/**
 * @author caozhixin
 * @date 2024/9/13 14:07
 *
 * 类级别的泛型适用于整个类的方法，而方法级别的泛型仅限于该方法。
 * 在设计时，如果某些类型在多个方法中都需要使用，可以考虑将其提升到类级别，反之则可以在方法内独立定义。
 *
 * 当需要在多个方法中共享泛型时，在类级别定义它们是合适的。
 * 当方法需要灵活性以返回不同的类型时，可以在方法级别声明泛型。
 */
public abstract class AbstractEsSupplierService<Condition extends AbstractEsCondition, ID> {
    public abstract Page<ID> search(Condition queryCondition);

    public abstract <R> Page<R> search(Condition queryCondition, Function<String, R> function);
}
