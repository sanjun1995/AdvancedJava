package com.example.demo.algo.greedy;

/**
 * @author caozhixin
 * @date 2022/9/3 10:10 PM
 */
public class JumpDemo {
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        int rightmost = 0;
        // i <= rightmost 含义 i目前能到达的地方
        for (int i = 0; i <= rightmost; i++) {
            int temp = i + nums[i];
            rightmost = Math.max(rightmost, temp);
            if (rightmost >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
//    public boolean canJump(int[] nums) {
//        int n = nums.length;
//        int rightmost = 0;
//        for (int i = 0; i < n; i++) {
//            if (i <= rightmost) {
//                rightmost = Math.max(rightmost, i + nums[i]);
//                if (rightmost >= n - 1) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
