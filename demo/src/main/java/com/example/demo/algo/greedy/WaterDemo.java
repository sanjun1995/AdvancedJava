package com.example.demo.algo.greedy;

/**
 * @author caozhixin
 * @date 2022/9/3 11:29 PM
 */
public class WaterDemo {
    public static void main(String[] args) {

    }

    public int maxArea(int[] a) {
        int max = 0;
        for (int i = 0, j = a.length - 1; i < j;) {
            int minHeight = a[i] < a[j] ? a[i++] : a[j--];
            // 前面有i++或j--，故需要+1
            max = Math.max(max, (j - i + 1) * minHeight);
        }
        return max;
    }

//    public int maxArea(int[] height) {
//        // 本质是求面积
//        int l = 0, r = height.length - 1;
//        int ans = 0;
//        while (l < r) {
//            int area = Math.min(height[l], height[r]) * (r - l);
//            ans = Math.max(ans, area);
//            if (height[l] <= height[r]) {
//                ++l;
//            } else {
//                --r;
//            }
//        }
//        return ans;
//    }
}
