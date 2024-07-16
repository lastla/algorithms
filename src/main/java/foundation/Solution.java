package foundation;

import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    public int findKthLargest(int[] nums, int k) {

        return quick(nums, 0, nums.length - 1, nums.length - k);


    }

    private int quick(int[] nums, int left, int right, int i) {
        int p = partition(nums, left, right);
        if (p == i) {
            return nums[p];
        }
        if (i < p) {
          return   quick(nums, left, p - 1, i);
        } else {
           return quick(nums, p + 1, right, i);
        }
    }
    int partition( int[] a, int left, int right){
        //随机基准点实现
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, idx, left);

        int pv = a[left]; //左边界元素为基准值
        int i = left + 1; //初始化i从左边第一个不为基准点找>=基准值的索引
        int j = right;//初始化j从右找<=基准值的索引

        while (i <= j) {
            // i 从左向右找>=基准点的
            while (i <= j && a[i] < pv) {
                i++;
            }
            // j 从右向左找<=基准点的
            while (i <= j && a[j] > pv) {
                j--;
            }
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }

        }
        swap(a, left, j);

        return j;
    }
    private void swap ( int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
