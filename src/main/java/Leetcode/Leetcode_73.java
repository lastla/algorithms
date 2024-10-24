package Leetcode;

import java.util.Arrays;

public class Leetcode_73 {
    public static void setZeroes(int[][] matrix) {
        int lenI = matrix.length;
        int lenJ = matrix[0].length;

        boolean[] m = new boolean[lenI];
        boolean[] n = new boolean[lenJ];

        for (int i = 0; i < lenI; i++) {
            for (int j = 0; j < lenJ; j++) {
                if(matrix[i][j]==0){
                    m[i] = true;
                    n[j] = true;
                }
            }
        }

        for (int i = 0; i < m.length; i++) {
            if(m[i]){
                for (int j = 0; j < lenJ; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n.length; i++) {
            if(n[i]){
                for (int j = 0; j < lenI; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
