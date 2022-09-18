package com.example.demo.algo;

import java.util.*;

/**
 * @author caozhixin
 * @date 2022/9/3 12:06 PM
 */
public class WindowDemo {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k  + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 连续递减时，deque必定会多起来，需要将队首去掉
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
//        if (nums == null || nums.length < 2) {
//            return nums;
//        }
//        LinkedList<Integer> queue = new LinkedList<>();
//        int[] result = new int[nums.length - k + 1];
//        for (int i = 0; i < nums.length; i++) {
//            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
//            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
//                queue.pollLast();
//            }
//            // 添加当前值对应的数组下标
//            queue.addLast(i);
//            // 把队首第一个元素去了
//            if (queue.peek() <= i - k) {
//                queue.poll();
//            }
//            // 当窗口长度为k时，保存当前窗口中最大值
//            if (i + 1 >= k) {
//                result[i + 1 - k] = nums[queue.peek()];
//            }
//        }
//        return result;
//        int n = nums.length;
//        // 优先级比较，比较值，相等的话，比较下标
//        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] -o1[1];
//            }
//        });
//        for (int i = 0; i < k; i++) {
//            pq.offer(new int[]{nums[i], i});
//        }
//        // 储存答案
//        int[] ans = new int[n - k + 1];
//        ans[0] = pq.peek()[0];
//        for (int i = k; i < n; i++) {
//            // 加入大顶堆
//            pq.offer(new int[]{nums[i], i});
//            while (pq.peek()[1] <= i - k) {
//                pq.poll();
//            }
//            ans[i - k + 1] = pq.peek()[0];
//        }
//        return ans;
    }
}
