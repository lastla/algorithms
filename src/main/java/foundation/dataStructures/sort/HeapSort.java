package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 *  建立大顶堆
 *  每次将堆顶元素（最大值）交换到末尾，调整堆顶元素，让它重新符合大顶堆特性
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 7, 6, 4, 5};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }



    private static void sort(int[] nums){
        heapify(nums,nums.length);
        for (int i = nums.length-1; i>0; i--) {
            swap(nums,0,i);
            down(nums,0,i);
        }
    }
    //建堆
    private static void heapify(int[] nums,int size){
        for (int i = size / 2 - 1; i >=0 ; i--) {  //最后一个叶子节点的父节点就是最后一个非叶子节点
            down(nums,i,size);
        }
    }


    private static void down(int[] nums,int parent,int size){
        while (true){
            int left = 2*parent+1;
            int right = left+1;
            int max = parent;

            if(left<size && nums[max]<nums[left]){
                max = left;
            }
            if(right<size && nums[max]<nums[right]){
                max = right;
            }
            if(parent==max){
                break;
            }
            swap(nums,parent,max);
            parent =max;
        }
    }

    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
