package foundation.dataStructures.sort;

import java.util.Arrays;

/**
 * 单边循环（lomuto分区）要点
 *
 * * 选择最右侧元素作为基准点
 * * j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
 *   * 交换时机：j 找到小的，且与 i 不相等
 *   * i 找到 >= 基准点元素后，不应自增
 * * 最后基准点与 i 交换，i 即为基准点最终索引
 */
public class QuickSortLomuto {

    public static void sort(int[] a){
        quick(a,0,a.length-1);
    }


    private static void quick(int[] a,int left,int right){
        if(left>=right){
            return;
        }

        int p = partition(a,left,right);//执行分区，返回值为基准点元素的索引

        quick(a,left,p-1);
        quick(a,p+1,right);
    }

    private static int partition(int[] a,int left,int right){
        int pv = a[right];//lomuto分区将最右侧的值当作基准点
        int j= left;//初始化j的值等于left，j 负责寻找小于基准点元素的值
        int i = left;//初始化i的值等于left，i 负责找大于基准点元素的值

        while (j<right){
            if(a[j] < pv){//j找到小于基准点的元素，i没有找到比基准点大的元素
                if(i!=j){
                    swap(a,i,j);
                }
                i++;
            }
            j++;
        }
        swap(a,i,right);
        return i;
    }

    private static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args) {
        int[] a = {5, 3, 7, 2, 9, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
