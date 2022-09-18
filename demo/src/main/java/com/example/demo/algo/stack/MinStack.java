package com.example.demo.algo.stack;

import java.util.Stack;

/**
 * @author caozhixin
 * @date 2022/9/18 11:00 AM
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.push(4);
        minStack.push(3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
    private Stack<int[]> stack = new Stack<>();

    public MinStack() {}

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
        } else {
            stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
//    Deque<Integer> xStack;
//    Deque<Integer> minStack;
//    public MinStack() {
//        xStack = new LinkedList<>();
//        minStack = new LinkedList<>();
//        minStack.push(Integer.MAX_VALUE);
//    }
//
//    public void push(int x) {
//        xStack.push(x);
//        // 每次都装入，只不过是最小值罢了
//        minStack.push(Math.min(minStack.peek(), x));
//    }
//
//    public void pop() {
//        xStack.pop();
//        minStack.pop();
//    }
//
//    public int top() {
//        return xStack.peek();
//    }
//
//    public int getMin() {
//        return minStack.peek();
//    }
}
