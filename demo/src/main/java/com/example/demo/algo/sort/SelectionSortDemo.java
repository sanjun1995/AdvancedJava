package com.example.demo.algo.sort;

/**
 * 选择排序
 */
public class SelectionSortDemo {
    public static void selectSort(int[] a) {
        //从小到大，选择待排序的第一个数作为最小的值min
        int minIndex; //最小值下标
        int minTemp; //临时存储最小值
        for (int i = 0; i < a.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                //找到临时最小值后，让它往后比较
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                minTemp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = minTemp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {14, 25, 36, 27, 36, 44, 2, 7, 9};
        selectSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
