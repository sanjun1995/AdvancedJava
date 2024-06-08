package com.example.demo.algo.sort;

/**
 * 插入排序
 * @author caozhixin
 */
public class InsertionSortDemo {
    public static void main(String[] args) {
        int[] arr = {21, 25, 14, 8, 3, 63, 77, 44, 66};
//        insertionSort(arr);
        binaryInsertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void binaryInsertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int left = 0;
            int right = i - 1;
            // 使用二分查找找到插入的位置
            int insertIndex = binarySearch(arr, key, left, right);

            // 将大于key的元素右移
            for (int j = i - 1; j >= insertIndex; j--) {
                arr[j + 1] = arr[j];
            }

            // 插入key
            arr[insertIndex] = key;
        }
    }

    private static int binarySearch(int[] arr, int key, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

//    private static void insertionSort(int[] arr) {
//        int preIndex, current;
//        for (int i = 1; i < arr.length; i++) {
//            preIndex = i - 1;
//            current = arr[i];
//            while (preIndex >= 0 && arr[preIndex] > current) {
//                arr[preIndex + 1] = arr[preIndex];
//                preIndex--;
//            }
//            arr[preIndex + 1] = current;
//        }
//    }
}
