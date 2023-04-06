package com.example.demo.algo.sort;

/**
 * @author caozhixin
 * @date 2023/4/5 22:04
 */
import java.util.Arrays;

public class ArraySortDemo {
    public static void main(String[] args) {
        int[] arr = {5, 7, 3, 9, 2, 1, 6, 4, 8};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
