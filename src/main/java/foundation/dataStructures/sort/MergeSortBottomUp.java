package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 * * 分 - 每次从中间切一刀，处理的数据少一半
 * * 治 - 当数据仅剩一个时可以认为有序
 * * 合 - 两个有序的结果，可以进行合并排序
 */
public class MergeSortBottomUp {

    private static void merge(int[] a1,int i,int iEnd,int j ,int jEnd,int[] a2){
        int k = i;
        while(i<=iEnd && j<=jEnd){
            if(a1[i]<a1[j]){
                a2[k] = a1[i];
                i++;
            }else{
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

    public static void sort(int[] a1){
        int n = a1.length;
        int[] a2 = new int[n];

        for (int width = 1; width <n ; width*=2) {
            // i j 分别代表待合并区间的左右边界
            for (int i = 0; i < n; i+=2*width) {
                int j = Math.min(i+2*width-1,n-1);//确保右边界不会超出索引
                //System.out.printf("width %d [%d,%d]%n",width,i,j);
                int m = Math.min(i+width-1,n-1);
                merge(a1,i,m,m+1,j,a2);
            }
            System.arraycopy(a2,0,a1,0,n);
        }
    }
    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
