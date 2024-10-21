package Leetcode;

// 动态规划
public class Leetcode_53 {

    public static int maxSubArray(int[] nums) {
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i] + nums[i - 1];
            if (temp > nums[i]) {
                nums[i] = temp;
            }
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
