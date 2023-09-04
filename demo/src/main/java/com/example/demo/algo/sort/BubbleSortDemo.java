package com.example.demo.algo.sort;

/**
 * 冒泡排序
 */
public class BubbleSortDemo {
    public static void bubbleSort(int[] a) {
        //一次遍历，长度a.length - 1，主要是第一个最终没影响
        for (int i = 1; i <= a.length - 1; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {14, 65, 25, 22, 1, 4, 78, 36, 50};
        bubbleSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

//    public void bubbleSort(int arr[]) {
//        boolean didSwap;
//        for (int i = 0, len = arr.length; i < len - 1; i++) {
//            didSwap = false;
//            for (int j = 0; j < len - i - 1; j++) {
//                if (arr[j + 1] < arr[j]) {
//                    swap(arr, j, j + 1);
//                    didSwap = true;
//                }
//            }
//            if (didSwap == false)
//                return;
//        }
//    }
}
