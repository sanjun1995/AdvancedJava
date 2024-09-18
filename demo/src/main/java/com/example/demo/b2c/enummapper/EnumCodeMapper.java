package com.example.demo.b2c.enummapper;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工具类，用于通过 code 值查找对应的枚举
 * @author caozhixin
 * @date 2024/9/18 11:28
 */
public class EnumCodeMapper {

    // 存储每个枚举类型对应的 code 值和枚举实例的映射关系
    private static Map<Class<?>, Map<Object, CodeMappable<?>>> enumTypeToCodeValues = new ConcurrentHashMap<>();

    // 查找指定枚举类型中对应 code 的枚举实例，默认如果找不到会抛出异常
    public static <C, E extends Enum<?> & CodeMappable<C>> E findByCode(Class<E> enumType, C code) {
        return findByCode(enumType, code, true);
    }

    // 查找指定枚举类型中对应 code 的枚举实例
    // throwIfNotFound 参数用于决定如果找不到枚举时是否抛出异常
    public static <C, E extends Enum<?> & CodeMappable<C>> E findByCode(Class<E> enumType, C code, boolean throwIfNotFound) {
        // 从缓存中获取当前枚举类型对应的 code 和枚举实例的映射表
        Map<Object, ?> codeValues = enumTypeToCodeValues.get(enumType);
        if (codeValues == null) {
            // 如果缓存中没有，初始化并缓存这个枚举类型的 code 映射表
            codeValues = initAndCache(enumType);
        }
        // 获取对应 code 的枚举值
        @SuppressWarnings("unchecked")
        E result = (E) codeValues.get(code);

        // 如果没有找到并且要求抛出异常
        if (result == null && throwIfNotFound) {
            throw new IllegalArgumentException(String.format("未知的 code: %s, 枚举类型=%s", code, enumType.getSimpleName()));
        }

        // 返回找到的枚举实例，或者返回 null
        return result;
    }

    // 初始化并缓存枚举类型的 code 和枚举实例的映射表
    private static <C, E extends Enum<?> & CodeMappable<C>> Map<Object, ?> initAndCache(Class<E> enumType) {
        // 为了避免多线程并发初始化，使用同步锁
        synchronized (enumType) {
            // 再次检查缓存中是否已有此枚举类型的映射表，避免重复初始化
            Map<Object, CodeMappable<?>> codeValues = enumTypeToCodeValues.get(enumType);
            if (codeValues != null) {
                return codeValues;
            }
            // 检查传入的类型是否为枚举类型
            if (!enumType.isEnum()) {
                throw new IllegalArgumentException("该类型不是枚举: " + enumType);
            }

            try {
                // 获取枚举类的 "values" 方法，返回所有枚举值
                Method valuesMethod = enumType.getDeclaredMethod("values");
                // 调用 values 方法，获取枚举实例数组
                CodeMappable<?>[] codeMappables = (CodeMappable<?>[]) valuesMethod.invoke(null);
                // 创建一个 HashMap 来存储 code 到枚举实例的映射
                codeValues = new HashMap<>(codeMappables.length);

                // 遍历每个枚举实例，将它的 code 值作为键，枚举实例作为值，存入映射表中
                for (CodeMappable<?> codeMappable : codeMappables) {
                    codeValues.put(codeMappable.code(), codeMappable);
                }

                // 将映射表设为不可修改的 Map 并缓存到 enumTypeToCodeValues 中
                enumTypeToCodeValues.put(enumType, Collections.unmodifiableMap(codeValues));

                // 返回生成的映射表
                return codeValues;
            } catch (Exception e) {
                // 如果反射调用过程出现异常，抛出运行时异常
                throw new RuntimeException(e);
            }
        }
    }
}
