package com.example.demo.algo.zhuan;

/**
 * @author caozhixin
 * @date 2023/7/29 00:05
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindromeNumber(12321));
    }

    private static boolean isPalindromeNumber(int x) {
        if (x < 0) {
            return false;
        }
        int reverse = 0;
        int temp = x;
        while (temp > 0) {
            reverse = reverse * 10 + temp % 10;
            temp = temp / 10;
        }
        return reverse == x;
    }
}
