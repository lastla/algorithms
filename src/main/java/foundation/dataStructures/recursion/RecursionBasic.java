package foundation.dataStructures.recursion;

public class RecursionBasic {
    public static void main(String[] args) {
        System.out.println(f(5));
        reStr(0,"abc");
    }
    //阶乘
    public static int f(int n){
        if(n==0){
            return 1;
        }
        if(n==1){
            return 1;
        }
        return n * f(n-1);
    }

    //反向打印字符串
    private static void reStr(int n,String str){
        if(n==str.length()){
            return;
        }
         reStr(n+1,str);
        System.out.println(str.charAt(n));
    }
}
