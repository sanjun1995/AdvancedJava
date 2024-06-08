package com.example.demo.algo.sort;

import java.util.Stack;

/**
 * 快速排序
 */
public class QuickSortDemo {
    static class Range {
        int low, high;

        Range(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
//        if (startIndex >= endIndex) {
//            return;
//        }
//        // 获取基准元素位置
//        int pivotIndex = partition(arr, startIndex, endIndex);
//        // 根据基准元素，分成两部分进行递归排序
//        quickSort(arr, startIndex, pivotIndex - 1);
//        quickSort(arr, pivotIndex + 1, endIndex);
        Stack<Range> stack = new Stack<>();
        stack.push(new Range(startIndex, endIndex));
        while (!stack.isEmpty()) {
            Range range = stack.pop();
            if (range.low < range.high) {
                int pi = partition(arr, range.low, range.high);
                stack.push(new Range(range.low, pi - 1));
                stack.push(new Range(pi + 1, range.high));
            }
        }
    }


    /**
     * 单边循环
     * @return
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第1个位置作为基准元素
        int pivot = arr[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                mark++;
                int tmp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = tmp;
            }
        }
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

//    public static int partition(int[] arr, int low, int high) {
//        //基准数
//        int pivot = arr[low];
//
//        while (low < high) {
//            //先从右向左扫描
//            while (low < high && arr[high] >= pivot) {
//                high--;
//            }
//
//            //把high位置的数放到pivot所在位置上 --- 非实际
//            arr[low] = arr[high];
//
//            while (low < high && arr[low] <= pivot) {
//                low++;
//            }
//            //把low位置的数放到pivot所在位置上 --- 非实际
//            arr[high] = arr[low];
//        }
//        //最后把pivot放到基准位置
//        arr[low] = pivot;
//        //返回中轴坐标
//        return low;
//    }

//    private static int partition(int[] arr, int startIndex, int endIndex) {
//        // 取第1个位置作为基准元素
//        int pivot = arr[startIndex];
//        int left = startIndex;
//        int right = endIndex;
//
//        while (left != right) {
//            while (left < right && arr[right] > pivot) {
//                right--;
//            }
//            while (left < right && arr[left] <= pivot) {
//                left++;
//            }
//            if (left < right) {
//                int tmp = arr[left];
//                arr[left] = arr[right];
//                arr[right] = tmp;
//            }
//        }
//
//        // pivot和指针重合点交换
//        arr[startIndex] = arr[left];
//        arr[left] = pivot;
//
//        return left;
//    }

    public static void main(String[] args) {
        int[] arr = {1, 25, 14, 3, 24, 33, 14, 88, 66};
        quickSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
