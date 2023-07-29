package com.example.demo.algo.window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/7/29 09:07
 */
public class Window {
    public static void main(String[] args) {
        System.out.println(window(new int[]{1, 3, -1, -3, 5, 3, -6, 7}, 1));
    }

    private static List<String> window(int[] nums, int sum) {
        List<String> result = new ArrayList<>();
        int windowSize = 1;
        // 窗口大小
        while (windowSize <= nums.length) {
            // 基于窗口大小，每次起始点需要移动的位置
            for (int i = 0; i <= nums.length - windowSize; i++) {
                int temp = 0;
                int[] window = new int[windowSize];
                // 求窗口内的和
                for (int j = i; j < i + windowSize; j++) {
                    window[j - i] = nums[j];
                    temp += nums[j];
                }
                if (temp == sum) {
                    result.add(Arrays.toString(window));
                }
            }
            windowSize++;
        }
        return result;
    }
}
