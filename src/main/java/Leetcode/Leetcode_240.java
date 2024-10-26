package Leetcode;

public class Leetcode_240 {

    //法一：每一行都使用一次二分查找
    public static boolean searchMatrix(int[][] matrix, int target) {

        for (int i = 0; i < matrix.length; i++) {
            int idx = binarySearch(matrix[i], target);
            if (matrix[i][idx] == target) {
                return true;
            }
        }
        return false;
    }

    static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (target >= arr[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Math.max(l - 1, 0);
    }

    // 法二：将矩阵逆时针旋转45°可以发现其有类似二叉树的性质，即向左变小，向右变大
//    public static boolean searchMatrix(int[][] matrix, int target) {
//        int i = 0;
//        int j = matrix[0].length - 1;
//        while (i < matrix.length && j >= 0) {
//            if (matrix[i][j] < target) {
//                i++;
//            } else if (matrix[i][j] > target) {
//                j--;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        System.out.println(searchMatrix(matrix, 19));
    }
}
