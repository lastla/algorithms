package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 * * *将数组分为两部分* *[0 .. low-1]  [low .. a.length-1]*
 *   * *左边* *[0 .. low-1]* *是已排序部分*
 *   * *右边* *[low .. a.length-1]* *是未排序部分*
 * * *每次从未排序区域取出* *low* *位置的元素*, *插入到已排序区域*
 */
public class Insertion {

    public static void main(String[] args) {
        int[] nums = new int[]{5,4,2,6,9};
        //insertionSort1(nums,1);
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    //插入排序， low代表未排序部分 （循环）
    public static void insertionSort(int[] nums){

        for (int low = 1; low < nums.length; low++) {
            int temp = nums[low];
            int i = low-1;//已排序区域
            while (i>=0 && nums[i]>temp){
                nums[i+1] = nums[i];
                i--;
            }
            if(i!=low-1){
                nums[i+1] = temp;
            }
        }
    }




    //插入排序， j代表未排序部分(递归)
    public static void insertionSort1(int[] nums,int j){
    if(j== nums.length){
        return;
    }
        int temp = nums[j];
        int i = j-1; //已排序区域的指针
        while (i>=0 && nums[i]>temp){//没有找到插入位置
            nums[i+1] = nums[i];//空出插入位置
            i--;
        }
        nums[i+1] = temp;//找到插入位置
        insertionSort1(nums,j+1);
    }
}
