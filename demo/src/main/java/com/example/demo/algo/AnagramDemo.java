//package com.example.demo.algo;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author caozhixin
// * @date 2022/9/18 11:09 PM
// */
//public class AnagramDemo {
//    public static void main(String[] args) {
//
//    }
//    public static boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//        Map<Character, Integer> table = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            table.put(ch, table.getOrDefault(ch, 0) + 1);
//        }
//        for (int i = 0; i < t.length(); i++) {
//            char ch = t.charAt(i);
//            table.put(ch, table.getOrDefault(ch, 0) - 1);
//            if (table.get(ch) < 0) {
//                return false;
//            }
//        }
//        return true;
////        if (s.length() != t.length()) {
////            return false;
////        }
////        int[] table = new int[26];
////        for (int i = 0; i < s.length(); i++) {
////            table[s.charAt(i) - 'a']++;
////        }
////        for (int i = 0; i < t.length(); i++) {
////            table[t.charAt(i) - 'a']--;
////            if (table[t.charAt(i) - 'a'] < 0) {
////                return false;
////            }
////        }
////        return true;
//    }
//}
