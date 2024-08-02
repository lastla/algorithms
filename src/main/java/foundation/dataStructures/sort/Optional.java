package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 * 每一轮选择，找出最大（最小）的元素，并把它交换到合适的位置
 */
public class Optional {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 2, 6, 9};
        int[] arr = new int[]{6, 5, 4, 3, 2, 1};
        //sort(nums,0);
        optionalSort(nums);
        optionalSort(arr);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(arr));
    }


    public static void optionalSort(int[] nums) {


        for (int i = nums.length - 1; i > 0; i--) {
            int maxIdx = i;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[maxIdx]) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                swap(nums, maxIdx, i);
            }

        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    //选择排序变体 j代表未排序部分
    public static void sort(int[] nums, int j) {
        if (j == nums.length - 1) {
            return;
        }
        int index = j;
        for (int i = j; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                index = i + 1;
            }
        }
        int temp = nums[j];
        nums[j] = nums[index];
        nums[index] = temp;

        sort(nums, j + 1);
    }
}
