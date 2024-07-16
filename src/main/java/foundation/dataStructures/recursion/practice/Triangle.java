package foundation.dataStructures.recursion.practice;

public class Triangle {

    public static void main(String[] args) {
       //System.out.println(element(4, 2));
       // print(8);
       // print1(6);
        print2(6);
    }

    //求[i+j]
    public static int element(int i, int j){
        if(j==0 || i==j){
            return 1;
        }

        return element(i-1,j-1) + element(i-1,j);
    }
    //打印杨辉三角
    public static void print(int n){
        for (int i = 0; i <n ; i++) {
            printSpace(n,i);
            for (int j = 0; j <=i ; j++) {
                System.out.printf("%-4d",element(i,j));
            }
            System.out.println();
        }
    }
    public static void printSpace(int n, int i){
        int nums = (n-1-i) *2;
        for (int j = 0; j < nums; j++) {
            System.out.print(" ");
        }
    }

    //优化1 memorization 借用二维数组
    public static int element1(int[][] triangle ,int i, int j){
        if(triangle[i][j]>0){//如果二维数组[i,j] > 0,说明已有此元素不用计算，直接返回
            return triangle[i][j];
        }
        if(j==0 || i==j){
            triangle[i][j]=1;
            return 1;
        }
        //将计算的额结果存到二维数组中
            triangle[i][j] = element1(triangle,i-1,j-1) + element1(triangle,i-1,j);
        return triangle[i][j];
    }
    //打印杨辉三角
    public static void print1(int n){
        int[][]triangle = new int[n][];//二维数组的行就等于n，列无法确定
        for (int i = 0; i <n ; i++) {
            triangle[i] = new int[i+1];//有第n行就有n+1列
            printSpace1(n,i);
            for (int j = 0; j <=i ; j++) {
                System.out.printf("%-4d",element1(triangle,i,j));
            }
            System.out.println();
        }
    }
    public static void printSpace1(int n, int i){
        int nums = (n-1-i) *2;
        for (int j = 0; j < nums; j++) {
            System.out.print(" ");
        }
    }

    //优化2 动态规划 使用一维数组  n是总层数 i代表第i层
    public static void createRow(int[] rows,int i){

        if(i==0){
            rows[0] =1;
            return;
        }
        for (int j = i; j >0 ; j--) {
            rows[j] = rows[j] + rows[j-1];
        }
    }

    //打印杨辉三角
    public static void print2(int n){
        int[] row = new int[n];
        for (int i = 0; i <n ; i++) { //行
            createRow(row,i);
            printSpace1(n,i);
            for (int j = 0; j <=i ; j++) {
                System.out.printf("%-4d",row[j]);
            }
            System.out.println();
        }
    }
}
