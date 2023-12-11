//package com.example.demo.algo;
//
//import java.util.Deque;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Map;
//
///**
// * @author caozhixin
// * @date 2022/9/17 9:21 PM
// */
//public class ValidParenthesesDemo {
//    public static void main(String[] args) {
//        System.out.println(isValid("()[()]"));
//    }
//    public static boolean isValid(String s) {
//        int n = s.length();
//        if (n % 2 == 1) {
//            return false;
//        }
//        Map<Character, Character> pairs = new HashMap<>() {{
//            put(')', '(');
//            put(']', '[');
//            put('}', '{');
//        }};
//        Deque<Character> stack = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            char ch = s.charAt(i);
//            if (pairs.containsKey(ch)) {
//                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
//                    return false;
//                }
//                stack.pop();
//            } else {
//                stack.push(ch);
//            }
//        }
//        return stack.isEmpty();
//    }
////    public static boolean isValid(String s) {
////        while (true) {
////            int len = s.length();
////            s = s.replace("()", "");
////            s = s.replace("{}", "");
////            s = s.replace("[]", "");
////            if (s.length() == len) {
////                return len == 0;
////            }
////        }
////    }
//}
