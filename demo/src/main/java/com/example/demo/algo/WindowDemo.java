package com.example.demo.algo;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author caozhixin
 * @date 2022/9/3 12:06 PM
 */
public class WindowDemo {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 优先级比较，比较值，相等的话，比较下标
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] -o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        // 储存答案
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; i++) {
            // 加入大顶堆
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}
