package com.example.demo.algo.sort;

/**
 * 快速排序
 */
public class QuickSortDemo {
    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int low, int high) {
        if (high < low) return;
        int j = partition(arr, low, high);
        sort(arr, low, j - 1);
        sort(arr, j + 1, high);
    }

    public static int partition(int[] arr, int low, int high) {
        //基准数
        int pivot = arr[low];

        while (low < high) {
            //先从右向左扫描
            while (low < high && arr[high] >= pivot) {
                high--;
            }

            //把high位置的数放到pivot所在位置上 --- 非实际
            arr[low] = arr[high];

            while (low < high && arr[low] <= pivot) {
                low++;
            }
            //把low位置的数放到pivot所在位置上 --- 非实际
            arr[high] = arr[low];
        }
        //最后把pivot放到基准位置
        arr[low] = pivot;
        //返回中轴坐标
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1, 25, 14, 3, 24, 33, 14, 88, 66};
        quickSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
