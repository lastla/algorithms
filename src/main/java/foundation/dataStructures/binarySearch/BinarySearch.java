package foundation.dataStructures.binarySearch;

import java.util.Arrays;

//二分查找
public class BinarySearch {
    //基础版 查找范围是[left,right]
    public static int binarySearchBasic(int[] a,int target){
        int left = 0;
        int right = a.length-1;
        int mid ;

        while (left <= right){
            mid = (left+right) / 2;//java自动向下取整
            if(target>a[mid]){
                left = mid +1;
            }else if(target<a[mid]){
                right = mid - 1;
            }else {
                return mid;
            }

        }

        return -1;
    }

    /*改动版 查找范围是[left,right) right只作为边界不参与比较
    * 最好情况时间复杂度O(1)
    最坏情况时间复杂度O(log(n))
    * */
    public static int binarySearchAlternative(int[] a,int target){
        int left = 0;
        int right = a.length;


        while (left < right){
           int mid = (left+right) >> 1;
            if(target>a[mid]){
                left = mid +1;
            }else if(target<a[mid]){
                right = mid ;
            }else {
                return mid;
            }

        }

        return -1;
    }

    /*
    平衡版，无论所查找数在左边还是右边都只需要比较一次，缺点是必须要等循环结束才能得到目标值
    最好情况时间复杂度O(log(n))
    最坏情况时间复杂度O(log(n))
    左边为什么不加1，因为左边可能是<=的情况
    右边为什么不加1，因为右边仅表示一个边界，不参与比较
     */
    public static int binarySearchBalance(int[] a, int target){
        int left= 0;
        int right = a.length;
        while (1 < right-left){
            int mid = (left+right) >>> 1;
            if(target<a[mid]){
                right = mid;
            }else {
                left = mid;
            }
        }
        if(target==a[left]){
            return left;
        }
        return -1;
    }

    /*
    leftMost
     */
    public static int binarySearchLeftMost(int[] a, int target){
        int left = 0;
        int right = a.length-1;
        int mid ;
        int candidate = -1;
        while (left <= right){
            mid = (left+right) >>> 1;
        if(target>a[mid]){
                left = mid +1;
            }else if(target<a[mid]){
                right = mid - 1;
            }else {
                candidate = mid;
                right = mid-1;
            }

        }

        return candidate;
    }
    /*
    rightMost
     */
    public static int binarySearchRightMost(int[] a, int target){
        int left = 0;
        int right = a.length-1;
        int mid ;
        int candidate = -1;
        while (left <= right){
            mid = (left+right) >>> 1;
            if(target>a[mid]){
                left = mid +1;
            }else if(target<a[mid]){
                right = mid - 1;
            }else {
                candidate = mid;
                left = mid + 1;
            }

        }

        return candidate;
    }

    /*
    leftMost 的改进返回一个有意义的数而不是-1
    return >=target最靠左的元素索引
     */
    public static int binarySearchLeftMost2(int[] a, int target){
        int left = 0;
        int right = a.length-1;
        int mid ;
        while (left <= right){
            mid = (left+right) >>> 1;
            if(target>a[mid]){
                left = mid +1;
            }else{
                right = mid - 1;
            }

        }

        return left;
    }

    /*
    rightMost 的改进返回一个有意义的数而不是-1
    return <=target最靠右的元素索引
     */

    public static int binarySearchRightMost2(int[] a, int target){
        int left = 0;
        int right = a.length-1;
        int mid ;
        while (left <= right){
            mid = (left+right) >>> 1;
            if(target>=a[mid]){
                left = mid +1;
            }else {
                right = mid - 1;
            }

        }

        return left -1;
    }

    public static void main(String[] args) {
     int[] arr = {1,3,4};
        System.out.println(binarySearchLeftMost2(arr, 2));
        System.out.println(binarySearchRightMost2(arr, 2));
    }
}
