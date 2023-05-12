package com.example.demo.design;

import lombok.Builder;

@Builder(builderMethodName = "customBuilder")
public class Person {
    private String name;
    private int age;

    public static PersonBuilder customBuilder() {
        return new PersonBuilder() {
            @Override
            public Person build() {
                // 在这里重写 build() 方法
                // ...
                return super.build();
            }
        };
    }
}
