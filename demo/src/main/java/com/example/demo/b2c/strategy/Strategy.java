package com.example.demo.b2c.strategy;

import java.util.Set;

/**
 * @author caozhixin
 * @date 2024/9/13 09:27
 */
public interface Strategy<C> {
    Set<C> strategyCodes();
}
