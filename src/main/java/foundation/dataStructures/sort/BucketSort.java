package foundation.dataStructures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {

    public static void main(String[] args) {
        int[] ages = {20, 18, 28, 66, 25, 31, 67, 30}; // 假设人类年龄 1~99
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
    public static void sort(int[] ages){
        List<Integer>[] buckets = new List[10];
        //初始化桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        for (int age : ages) {
            buckets[age/10].add(age);
        }
        int k = 0;
        //排序桶类元素
        for (List<Integer> bucket : buckets) {
            int[] array = bucket.stream().mapToInt(Integer::valueOf).toArray();
            insertionSort(array);
            for (Integer v : array) {
                ages[k++] = v;
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
