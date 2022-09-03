package com.example.demo.algo.greedy;

/**
 * @author caozhixin
 * @date 2022/9/3 9:13 PM
 */
public class GreedyDemo {
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

//    public static int maxProfit(int[] prices) {
//        int maxProfit = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            for (int j = i + 1; j < prices.length; j++) {
//                int profit = prices[j] - prices[i];
//                if (profit > maxProfit) {
//                    maxProfit = profit;
//                }
//            }
//        }
//        return maxProfit;
//    }
}
