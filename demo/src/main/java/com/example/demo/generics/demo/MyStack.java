package com.example.demo.generics.demo;

import java.util.List;
import java.util.Stack;

/**
 * @author caozhixin
 * @date 2024/12/8 00:30
 */
public class MyStack<E> extends Stack<E> {
    public void pushAll(List<E> fruits) {
        for (E fruit : fruits) {
            push(fruit);
        }
    }

    public void pushAll2(List<? extends E> fruits) {
        for (E fruit : fruits) {
            push(fruit);
        }
    }

    public void popAll(List<E> fruits) {
        while (!isEmpty()) {
            fruits.add(pop());
        }
    }

    public void popAll2(List<? super E> fruits) {
        while (!isEmpty()) {
            fruits.add(pop());
        }
    }
}
