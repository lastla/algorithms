package foundation.dataStructures.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortDuplicate {

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
     ** 核心思想是
     *   * 改进前，i 只找大于的，j 会找小于等于的。一个不找等于、一个找等于，势必导致等于的值分布不平衡
     *   * 改进后，二者都会找等于的交换，等于的值会平衡分布在基准点两边
     *
     * * 细节：
     *   * 因为一开始 i 就可能等于 j，因此外层循环需要加等于条件保证至少进入一次，让 j 能减到正确位置
     *   * 内层 while 循环中 i <= j 的 = 也不能去掉，因为 i == j 时也要做一次与基准点的判断，好让 i 及 j 正确
     *   * i == j 时，也要做一次 i++ 和 j-- 使下次循环二者不等才能退出
     *   * 因为最后退出循环时 i 会大于 j，因此最终与基准点交换的是 j
     *
     * * 内层两个 while 循环的先后顺序不再重要
     */
    private static int partition(int[] a, int left,int right){
        //随机基准点实现
        int idx = ThreadLocalRandom.current().nextInt(right-left +1) + left;
        swap(a,idx,left);

        int pv = a[left]; //左边界元素为基准值
        int i = left+1; //初始化i从左边第一个不为基准点找>=基准值的索引
        int j = right;//初始化j从右找<=基准值的索引

        while (i<=j){
            // i 从左向右找>=基准点的
            while (i<=j && a[i] < pv){
                i++;
            }
            // j 从右向左找<=基准点的
            while (i<=j && a[j] > pv){
                j--;
            }
            if(i<=j){
                swap(a,i,j);
                i++;
                j--;
            }

        }
        swap(a,left,j);

        return j;
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
