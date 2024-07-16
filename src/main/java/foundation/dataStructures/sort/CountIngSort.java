package foundation.dataStructures.sort;

import java.util.Arrays;

public class CountIngSort {
    public static void main(String[] args) {
        int[] a = {5, 1, 1, 3, 0, -1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
    /*
        要点
        1. 让原始数组的最小值映射到 count[0] 最大值映射到 count 最右侧
        2. 原始数组元素 - 最小值 = count 索引
        3. count 索引 + 最小值 = 原始数组元素
     */

    public static void sort(int[] a){
        int max = a[0];
        int min =a[0];
        for (int i = 0; i < a.length; i++) {
            if(a[i]>max){
                max = a[i];
            }
            if(a[i]<min){
                min = a[i];
            }
        }
        int[] count = new int[max - min +1];

        for (int v : a) {
            count[v-min]++;
        }
        int k = 0;//原始数组的索引
        for (int i = 0; i < count.length; i++) {
            // i代表原始数组的元素  count[i]代表元素出现的次数
            while (count[i]>0){
                a[k] = i+min;
                count[i]--;
                k++;
            }

        }
    }
}
