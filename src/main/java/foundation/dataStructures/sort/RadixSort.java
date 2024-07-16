package foundation.dataStructures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    public static void main(String[] args) {
        int[] nums = {3, 6, 16000, 1,2,3,5,6,7,4,5,867,6754,324};
        System.out.println(Arrays.toString(nums));
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    public static void sort(int[] nums){
        int max = nums[0];
        //找最大值
        for (int i = 1; i <nums.length ; i++) {
            if(nums[i]>max){
                max = nums[i];
            }
        }
        //创建桶
        List<Integer>[] buckets = new List[10];
        //初始化桶
        for (int i = 0; i < 10; i++) {
            buckets[i] = new ArrayList<>();
        }
        //从低位向高位依次排序
        long exp = 1;//作为除数求每位的值

        while (exp<=max){
            int k = 0;
            //放入桶
            for (int i : nums) {
                buckets[i/(int)exp % 10].add(i);
            }

            for (List<Integer> bucket : buckets) {
                for (Integer num : bucket){
                    nums[k++] = num;
                }
                bucket.clear();
            }
            exp*=10;
        }
    }
}
