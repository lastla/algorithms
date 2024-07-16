package foundation.dataStructures.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortHoare {

    public static void sort(int[] a){
        quick(a,0,a.length-1);
    }

    public static void quick(int[] a,int left,int right){
        if(left>=right){
            return;
        }

        int p = partition(a,left,right);
        quick(a,left,p-1);
        quick(a,p+1,right);
    }

    /**
     * 双边快排注意事项
     *  1.内层循环中也要判断i<j，防止一轮外层循环中i>j
     *  2. 先循环j找小于基准值的索引，否则最后ij停下来的位置是大于基准值的索引，最后交换基准点元素时就错了
     *  3. 建议随机元素作为基准点 减少出现极度不平衡的概率
     *  4. 如果有大量重复元素也会造成极度不平衡
     */
    private static int partition(int[] a, int left,int right){
        //随机基准点实现
        int idx = ThreadLocalRandom.current().nextInt(right-left +1) + left;
        swap(a,idx,left);

        int pv = a[left]; //左边界元素为基准值
        int i = left; //初始化i从左找大于基准值的索引
        int j = right;//初始化j从右找小于基准值的索引

        while (i<j){

            while (i<j && a[j] >pv){
                j--;
            }
            while (i<j && a[i] <= pv){
                i++;
            }
            swap(a,i,j);
        }
        swap(a,left,i);
        return i;
    }

    private static void swap(int[] a,int i, int j){
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
