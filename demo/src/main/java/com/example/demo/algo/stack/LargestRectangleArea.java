package com.example.demo.algo.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author caozhixin
 * @date 2022/9/18 11:32 AM
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
    }
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int res = 0;

        int [] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
//        int res = 0;
//        Deque<Integer> stack = new ArrayDeque<>();
//        int [] newHeights = new int[heights.length + 2];
//        for (int i = 1; i < heights.length + 1; i++) {
//            newHeights[i] = heights[i - 1];
//        }
//        for (int i = 0; i < newHeights.length; i++) {
//            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
//                int cur = stack.pop();
//                int l = stack.peek();
//                int r = i;
//                res = Math.max(res, (r - l - 1) * newHeights[cur]);
//            }
//            stack.push(i);
//        }
//        return res;
//        int n = heights.length;
//        int[] left = new int[n];
//        int[] right = new int[n];
//
//        Deque<Integer> deque = new ArrayDeque<>();
//        // deque栈每个位置只会操作两次
//        for (int i = 0; i < n; i++) {
//            while (!deque.isEmpty() && heights[deque.peek()] >= heights[i]) {
//                deque.pop();
//            }
//            left[i] = deque.isEmpty() ? -1 : deque.peek();
//            deque.push(i);
//        }
//
//        deque.clear();
//        for (int i = n - 1; i >= 0; i--) {
//            while (!deque.isEmpty() && heights[deque.peek()] >= heights[i]) {
//                deque.pop();
//            }
//            right[i] = deque.isEmpty() ? n : deque.peek();
//            deque.push(i);
//        }
//
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
//        }
//        return ans;
//        int len = heights.length;
//        if (len == 0) {
//            return 0;
//        }
//        int res = 0;
//        for (int i = 0; i < len; i++) {
//            // 找左边最后一个大于等于heights[i]的下标
//            int left = i;
//            int curHeight = heights[i];
//            while (left > 0 && heights[left - 1] >= curHeight) {
//                left--;
//            }
//            // 找右边最后一个大于等于heights[i]的下标
//            int right = i;
//            while (right < len - 1 && heights[right + 1] >= curHeight) {
//                right++;
//            }
//            int width = right - left + 1;
//            res = Math.max(res, width * curHeight);
//        }
//        return res;
    }
}
