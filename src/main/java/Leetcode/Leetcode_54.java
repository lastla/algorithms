package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        //l:左  r:右 t:上 b:下
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        List<Integer> res = new ArrayList<>();

        while (true) {
            for (int i = l; i <= r; i++) res.add(matrix[l][i]);//从左往右
            if (++t > b) break;
            for (int i = t; i <= b; i++) res.add(matrix[i][r]);//从上到下
            if (l > --r) break;
            for (int i = r; i >= l; i--) res.add(matrix[b][i]);//从右往左
            if (t > --b) break;
            for (int i = b; i >= t; i--) res.add(matrix[i][l]);//从下到上
            if (++l > r) break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        System.out.println(spiralOrder(matrix));
    }
}
