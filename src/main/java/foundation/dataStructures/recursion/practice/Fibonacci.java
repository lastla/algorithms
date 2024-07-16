package foundation.dataStructures.recursion.practice;

import java.util.Arrays;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacciSequence(8));
        //System.out.println(rabbit(6));
       // System.out.println(flog(4));
        System.out.println(memorization(8));
    }


    public static int fibonacciSequence(int n){
        if(n==1){
            return 1;
        }
        if(n==0){
            return 0;
        }

        return fibonacciSequence(n-1) + fibonacciSequence(n-2);

    }
    //memorization记忆优化法
    public static int memorization(int n){
        int[] cache = new int[n+1];//定义一个数组用来缓存f(n)的值
        Arrays.fill(cache,-1);//初始化数组内容为-1，更斐波那契数列的第0项的0区分开
        cache[0] = 0;
        cache[1] = 1;
        return fibonacci(n,cache);
    }

    public static int fibonacci(int n,int[] cache){
        if (cache[n]!=-1){ //cache[n]不等于-1说明表中有f(n)的值不需要计算，直接返回即可
            return cache[n];
        }

        int x = fibonacci(n - 1, cache);//第n-1项的值
        int y = fibonacci(n - 2, cache);//第n-2项的值
        cache[n] = x + y;//x+y等于第n项
        return cache[n];
    }

    public static int rabbit(int n){
        if(n==2){
            return 1;
        }
        if(n==1){
            return 1;
        }

        return rabbit(n-1) +rabbit(n-2);
    }
    public static int flog(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }

        return flog(n-1) + flog(n-2);
    }
}
