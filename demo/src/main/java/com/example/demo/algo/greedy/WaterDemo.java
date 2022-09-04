package com.example.demo.algo.greedy;

/**
 * @author caozhixin
 * @date 2022/9/3 11:29 PM
 */
public class WaterDemo {
    public static void main(String[] args) {

    }

    public int maxArea(int[] height) {
        // 本质是求面积
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
