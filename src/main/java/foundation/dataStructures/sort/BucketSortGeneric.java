package foundation.dataStructures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSortGeneric {
    public static void main(String[] args) {
        int[] ages = {5,2,3,1,100000}; // 假设人类年龄 1~99
        System.out.println(Arrays.toString(ages));
        sort(ages);
        System.out.println(Arrays.toString(ages));
    }

    /*
        0
        1   18
        2   20 25 28
        3   31
        4
        5
        6   66 67
        7
        8
        9
     */

    /**
     *
     * @param a
     *  range 每个桶元素的最大数量
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
        int range = Math.max((max-min)/a.length-1,1);
        List<Integer>[] buckets = new List[(max-min)/range+1];
        //初始化桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int v : a) {
            buckets[(v-min)/range].add(v);
        }
        int k = 0;
        //排序桶类元素
        for (List<Integer> bucket : buckets) {
            System.out.println(bucket);
            int[] array = bucket.stream().mapToInt(Integer::valueOf).toArray();
            insertionSort(array);
            for (Integer v : array) {
                a[k++] = v;
            }
        }
    }

    public static void insertionSort(int[] a){
        for (int low = 1; low < a.length; low++) {
            int temp = a[low];
            int i = low-1;
            while(i>=0 && a[i]>temp){
                a[i+1] = a[i];
                i--;
            }
            a[i+1] = temp;
        }
    }
}
