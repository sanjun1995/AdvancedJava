package com.example.demo.algo.sort;

import java.util.Arrays;

/**
 * @author caozhixin
 * @date 2024/6/9 00:30
 */
public class CountSortDemo {
    public static void main(String[] args) {
//        int[] arr = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] arr = new int[]{95,94,91,98,99,90,99, 93,91,92};
        int[] sortedArray = countSort(arr);
        System.out.println(Arrays.toString(sortedArray));
    }

    private static int[] countSort(int[] arr) {
        // 得到数列的最大值和最小值，并算出差值d
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int d = max - min;
        // 创建统计数组并统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i] - min]++;
        }

        // 统计数组做变形，后面的元素等于前面的元素之和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        // 倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortedArray[countArray[arr[i] - min] - 1] = arr[i];
            countArray[arr[i] - min]--;
        }
        return sortedArray;
    }

//    private static int[] countSort(int[] arr) {
//        // 得到数列的最大值
//        int max = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[i] > max) {
//                max = arr[i];
//            }
//        }
//
//        // 根据数列最大值确定统计数组的长度
//        int[] countArray = new int[max + 1];
//        // 遍历数列，填充统计数组
//        for (int i = 0; i < arr.length; i++) {
//            countArray[arr[i]]++;
//        }
//        // 遍历统计数组，输出结果
//        int index = 0;
//        int[] sortedArray = new int[arr.length];
//        for (int i = 0; i < countArray.length; i++) {
//            for (int j = 0; j < countArray[i]; j++) {
//                sortedArray[index++] = i;
//            }
//        }
//        return sortedArray;
//    }
}
