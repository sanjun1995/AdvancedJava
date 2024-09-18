package com.example.demo.b2c.snapshot;

import java.util.Objects;

/**
 * @author caozhixin
 * @date 2024/9/18 13:11
 */
public interface Entity<T> {
    T getId();

    default boolean snapshotBeforeProcess() {
        return true;
    }

    default <T> boolean persistent(T property, T snapshotProperty, boolean changedRequired) {
        if (changedRequired) {
            return !Objects.equals(property, snapshotProperty);
        }
        return property != null;
    }
}
