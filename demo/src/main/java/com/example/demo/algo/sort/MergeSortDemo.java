package com.example.demo.algo.sort;

/**
 * 归并排序
 */
public class MergeSortDemo {
    public static void merge(int[] a, int low, int mid, int high) {
        //建立一个临时数组，大小为归并后大小
        int[] temp = new int[high - low + 1];
        //左边
        int i = low;
        //右边
        int j = mid + 1;
        //新数组的起始下标
        int k = 0;
        //把较小的数先移到新数组
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        //左边剩余
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        //右边剩余
        while (j <= high) {
            temp[k++] = a[j++];
        }
        //把新数组复制到原数组
        for (int index = 0; index < temp.length; index++) {
            //从分点开始
            a[index + low] = temp[index];
        }

    }

    public static void mergeSort(int[] a, int low, int high) {
        //取中点
        int mid = (low + high) / 2;
        if (low < high) {
            //左边
            mergeSort(a, low, mid);
            //右边
            mergeSort(a, mid + 1, high);
            //归并
            merge(a, low, mid, high);
        }
    }

    public static void main(String[] args) {
        int[] a = {21, 25, 14, 8, 3, 63, 77, 44, 66};
        mergeSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
