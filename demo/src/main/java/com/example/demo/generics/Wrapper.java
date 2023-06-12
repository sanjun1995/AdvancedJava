package com.example.demo.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/6/12 19:28
 */
public class Wrapper<T> {
    public static <T> Type getGenericRuntimeType(Wrapper<T> wrapper) {
        Type type = wrapper.getClass().getGenericSuperclass();
        if (type == null) {
            return null;
        }

        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType)type).getActualTypeArguments();
            return types[0];
        }
        return null;
    }

    public static void main(String[] args) {
        Type type1 = getGenericRuntimeType(new Wrapper<List<String>>());
        Type type2 = getGenericRuntimeType(new Wrapper<List<String>>() {
        });

        System.out.println(type1);
        System.out.println(type2);
    }
}
