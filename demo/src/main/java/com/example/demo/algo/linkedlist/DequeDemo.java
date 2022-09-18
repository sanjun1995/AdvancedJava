package com.example.demo.algo.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caozhixin
 * @date 2022/9/17 5:59 PM
 */
public class DequeDemo {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.push("a");
        deque.push("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }
}
