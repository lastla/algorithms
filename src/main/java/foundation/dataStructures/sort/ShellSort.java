package foundation.dataStructures.sort;

import java.util.Arrays;

/** 希尔排序
 * 将数组中个元素按照分组进行插入排序，可以减少交换次数
 * * 简单的说，就是分组实现插入，每组元素间隙称为 gap
 * * 每轮排序后 gap 逐渐变小，直至 gap 为 1 完成排序
 * * 对插入排序的优化，让元素更快速地交换到最终位置
 */
public class ShellSort {

    public static void sort(int[] nums){
        //gap(间隙的选择) nums.length/2
        for (int gap = nums.length >> 1; gap >=1 ; gap = gap >> 1) {

            for (int low = gap; low < nums.length ; low++) {
                int temp = nums[low];
                int i = low - gap;
                while (i>=0 && nums[i]>temp){
                    nums[i+gap] = nums[i];
                    i-=gap;
                }
                if(i!=low-gap) nums[i+gap] = temp;

            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,4,2,6,9};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
