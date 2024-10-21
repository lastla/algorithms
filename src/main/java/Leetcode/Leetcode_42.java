package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Leetcode_42 {
    public static int trap(int[] height) {

        Deque<Integer> deque = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!deque.isEmpty() && height[i] > height[deque.peek()]){
                int pop = deque.pop();
                if(deque.isEmpty()){ //栈为空说明左边没有可以围住雨水的柱子了
                    break;
                }
                int peek = deque.peek();
                //高度为左边柱子和右边柱子的高度中取最小值-减去中间柱子高度
                int high = Math.min(height[peek],height[i]) - height[pop];
                int width = i - peek - 1;

                res += high * width;
            }
            deque.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {4,2,0,3,2,5};
        int v = trap(heights);
        System.out.println(v);
    }
}
