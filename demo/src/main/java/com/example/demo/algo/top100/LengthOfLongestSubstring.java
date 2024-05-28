package com.example.demo.algo.top100;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2024/5/28 14:29
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // 当right指针指向的字符已经在charSet中时，左指针向右移动并从charSet中移除相应字符
            while (charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            // 将right指针指向的字符加入charSet，并更新maxLength
            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
