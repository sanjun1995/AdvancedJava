package com.example.demo.algo.byte_dance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author caozhixin
 * @date 2025/3/1 13:04
 */
public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        int maxLength = Integer.MIN_VALUE;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>(16);
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = map.get(c) + 1;
            }
            map.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
