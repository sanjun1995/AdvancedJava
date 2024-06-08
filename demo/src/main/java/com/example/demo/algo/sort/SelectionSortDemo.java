package com.example.demo.algo.sort;

/**
 * 选择排序
 */
public class SelectionSortDemo {

//    public static void selectionSort(int[] arr) {
//        int n = arr.length;
//        for (int i = 0; i < n - 1; i++) {
//            int minIndex = i;
//            // 找到未排序部分的最小元素
//            for (int j = i + 1; j < n; j++) {
//                if (arr[j] < arr[minIndex]) {
//                    minIndex = j;
//                }
//            }
//            // 交换最小元素和当前元素
//            int temp = arr[minIndex];
//            arr[minIndex] = arr[i];
//            arr[i] = temp;
//        }
//    }

    public static void main(String[] args) {
        int[] arr = {14, 25, 36, 27, 36, 44, 2, 7, 9};
        stableSelectionSort(arr);
//        selectionSort(a);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void stableSelectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // 找到未排序部分的最小元素
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int minValue = arr[minIndex];
            while (minIndex > i) {
                arr[minIndex] = arr[minIndex - 1];
                minIndex--;
            }
            arr[i] = minValue;
        }
    }
}
