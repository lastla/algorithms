package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 * 每轮冒泡不断地比较**相邻**的两个元素，如果它们是逆序的，则交换它们的位置
 *  下一轮冒泡，可以调整未排序的右边界，减少不必要比较
 */
public class Bubble {

    public static void main(String[] args) {
        int[] nums = new int[]{5,4,2,6,9};
       // bubbleSort(nums, nums.length-1);
        bubbleSort2(nums);
        System.out.println(Arrays.toString(nums));
    }

    //递归实现  j代表未排序区域的右边界
    public static void bubbleSort(int[] nums,int j){
        if(j==0){
            return;
        }
        for (int i=0;i<j;i++){
            if(nums[i]>nums[i+1]){
                int temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }
        }

        bubbleSort(nums,j-1);
    }

    //优化 用变量x来充当已排序的边界，如x未改变则说明左边已经排序了就不用继续递归排序了
    public static void bubbleSort3(int[] nums,int j){
        if(j==0){
            return;
        }
        int x = 0;
        for (int i=0;i<j;i++){
            if(nums[i]>nums[i+1]){
                int temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
                x=i;
            }
        }

        bubbleSort(nums,x);
    }

    //循环实现
    public static void bubbleSort2(int[] nums){
      for (int j = nums.length-1;j>0;j--) {
            for (int i=0;i<j;i++){
                if(nums[i]>nums[i+1]){
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                }
            }
        }

    }
    // 循环优化版
    public static void bubbleSort3(int[] nums){
        int x = 0;//记录上一次排序发送交换的位置
        int j = nums.length -1;
        while(true){

            for (int i = 0; i < j; i++) {
                if(nums[i]>nums[i+1]){
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                    x = i;
                }
            }
            j = x;
            if(j==0){
                break;
            }

        }

    }
}
