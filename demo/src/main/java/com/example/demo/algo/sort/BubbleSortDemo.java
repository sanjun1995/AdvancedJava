package com.example.demo.algo.sort;

/**
 * 冒泡排序
 *
 * @author caozhixin
 */
public class BubbleSortDemo {
//    static void bubbleSort(int[] nums) {
//        int length = nums.length;
//        for (int i = 0; i < length - 1; i++) {
//            for (int j = 0; j < length - i - 1; j++) {
//                if (nums[j] > nums[j + 1]) {
//                    int tmp = nums[j];
//                    nums[j] = nums[j + 1];
//                    nums[j + 1] = tmp;
//                }
//            }
//        }
//    }

//    public static void bubbleSort(int[] arr) {
//        int n = arr.length;
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//    }

//    public static void bubbleSort(int[] arr) {
//        int n = arr.length;
//        for (int i = 0; i < n - 1; i++) {
//            boolean swapped = false;
//            for (int j = 0; j < n - i - 1; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                    swapped = true;
//                }
//            }
//            if (!swapped) {
//                break;
//            }
//        }
//    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int newN;
        do {
            newN = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i-1] > arr[i]) {
                    int temp = arr[i -  1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    newN = i;
                }
            }
        } while (newN != 0);
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
