package com.example.demo.algo;

/**
 * @author caozhixin
 * @date 2022/9/14 11:14 PM
 */
public class ClimbStairsDemo {
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs(int n) {
        /**
         * 1：1
         * 2：2
         * 3：f(1) + f(2)
         * 4：f(2) + f(3)
         * f(n)：f(n-1) + f(n-2)
         */
        // 最近 重复子问题 for while recursion递归（循环♻️）
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
