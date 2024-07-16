package foundation.dataStructures.recursion;

public class RecursionBinarySearch {
    public static void main(String[] args) {

        int[] nums = new int[]{1,2,4,5,9};
        System.out.println(search(nums, 5));
    }

    public static int search(int[] nums, int target){
      return   binarySearch(nums,target,0,nums.length-1);
    }
    //递归实现二分查找
    private static int binarySearch(int[] nums,int target,int left,int right){
        if(left>right){
            return -1;
        }
        int mid = (left + right) >>>1;

        if(target<nums[mid]){
            return binarySearch(nums,target,left,right-1);
        } else if (target>nums[mid]) {
            return binarySearch(nums,target,left+1,right);
        }else {
            return mid;
        }

    }
}
