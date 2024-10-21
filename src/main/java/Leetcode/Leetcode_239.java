package Leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Leetcode_239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];

        //初始化单调队列，，保证第一次队列里有最大值或第二大值，为后续窗口移动时移出元素做准备
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) deque.removeLast();
            deque.addLast(nums[i]);
        }

        res[0] = deque.peekFirst();

        int l = 1; //l是res的指针，l - 1为指向本次需要滑出窗口的索引，
        int r = k;  // r为本次移动到的索引

        while (r < n) {

            if (!deque.isEmpty() && nums[l - 1] == deque.peekFirst()) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[r] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[r]);
            res[l] = deque.peekFirst();
            l++;
            r++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] maxed = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(maxed));
    }
}
