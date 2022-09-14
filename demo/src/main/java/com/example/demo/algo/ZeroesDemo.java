package com.example.demo.algo;

import java.util.Arrays;

/**
 * @author caozhixin
 * @date 2022/9/14 10:33 PM
 */
public class ZeroesDemo {
    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 0, 3, 12};
        moveZeroe(a);
        System.out.println(Arrays.toString(a));
    }

    public static void moveZeroe(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 遍历元素，不为0，即赋值在nums[j]
                nums[j] = nums[i];
                // 如果i == j，说明当前位置不用动，否则置为0
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
     }
}
