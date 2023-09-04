package com.example.demo.algo.sort;

/**
 * 插入排序
 */
public class InsertionSortDemo {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i]; //保存当前位置i的元素，其中[0，p - 1]已经有序
            int j;
            for (j = i; j > 0; j--) {
                if (temp < arr[j - 1]) {
                    arr[j] = arr[j - 1]; //后移一位
                } else {
                    break; //如果temp大于前面的事，退出循环，直接插入
                }
            }
            arr[j] = temp; //插入到合适的位置
        }
    }

    public static void main(String[] args) {
        int[] a = {21, 25, 14, 8, 3, 63, 77, 44, 66};
        insertSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
