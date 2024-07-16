package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 * 归并排序（适合数据量大时进行排序） + 插入排序(适合数据量较小时进行排序)
 */
public class MergeInsertionSort {
    //插入
    public static void insertionSort(int[] nums,int left,int right){

        for (int low = left+1; low <= right; low++) {
            int temp = nums[low];
            int i = low-1;//已排序区域
            while (i>=left && nums[i]>temp){
                nums[i+1] = nums[i];
                i--;
            }
            if(i!=low-1){
                nums[i+1] = temp;
            }
        }
    }
    public static void merge(int[] a1,int i,int iEnd,int j, int jEnd,int[] a2){
        int k = i;
        while (i<=iEnd && j<=jEnd){
            if(a1[i]<a1[j]){
                a2[k] = a1[i];
                i++;
            }else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if(i>iEnd){
            System.arraycopy(a1,j,a2,k,jEnd-j+1);
        }
        if(j>jEnd){
            System.arraycopy(a1,i,a2,k,iEnd-i+1);
        }
    }
    //递归
    public static void sort(int[] a1){
        int[] a2 = new int[a1.length];
        split(a1,0, a1.length-1, a2);
    }

    private static void split(int[] a1,int left,int right,int[] a2){
        //治 当数据量小于32时使用插入排序提升性能
        if(right-left<=32){
            insertionSort(a1,left,right);
            return;
        }
        //分
        int m = (left+right) >>> 1;
        split(a1,left,m,a2);
        split(a1,m+1,right,a2);
        //合
        merge(a1,left,m,m+1,right,a2);
        System.arraycopy(a2,left,a1,left,right-left+1);
    }
    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));

    }

}
