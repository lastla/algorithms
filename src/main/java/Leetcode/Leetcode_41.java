package Leetcode;

public class Leetcode_41 {
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            //通过绝对值的方式防止标记的元素的影响
            int num = Math.abs(nums[i]);
            if (num <= len) { // 从0开始
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;

    }

    //置换元素的方法
//    public static int firstMissingPositive(int[] nums) {
//        int len = nums.length;
//        //将元素置换到正确的位置
//        for (int i = 0; i < len; i++) {
//            while (nums[i]>0 && nums[i]<=len && nums[nums[i]-1]!=nums[i]){
//                int temp = nums[nums[i] -1];
//                nums[nums[i] -1] = nums[i];
//                nums[i] = temp;
//            }
//        }
//
//        for (int i = 0; i < len; i++) {
//            if(nums[i]!=i+1){
//                return i+1;
//            }
//        }
//        return len+1;
//    }

    public static void main(String[] args) {
        int[] nums = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));
    }
}
