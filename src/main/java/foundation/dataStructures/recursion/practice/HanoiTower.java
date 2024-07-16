package foundation.dataStructures.recursion.practice;

import java.util.Arrays;
import java.util.LinkedList;

public class HanoiTower {
    //三个链表代表三根柱子
    public static LinkedList<Integer> a = new LinkedList<>();
    public static LinkedList<Integer> b = new LinkedList<>();
    public static LinkedList<Integer> c = new LinkedList<>();

    public static void main(String[] args) {
        int  n = 3;
        init(n);
        print();
        move(n,a,b,c);
        print();
    }

    /**
     *
     * @param n 移动圆盘的个数
     * @param a 源柱子
     * @param b 借用柱子
     * @param c 目标柱子
     */
    public static void move(int n,LinkedList<Integer> a,
                            LinkedList<Integer> b,
                            LinkedList<Integer> c){
        if(n==0){
            return;
        }
            move(n-1,a,c,b);//第一步，将n-1个盘子移动到借用柱子
            c.addLast(a.removeLast());//第二步 将最大圆盘移动到目标柱
            print();
            move(n-1,b,a,c);//第三步将n-1个盘子移动到目标柱子
    }

    //初始化柱子  数字就代表圆盘的大小 链表首部代表柱子的底，链表尾部代表柱子的顶部
    //链表的尾部弹出一个数据，连一个链表的尾部加一个数据代表移动圆盘
    public static void init(int n){

        for (int i = n; i >=1 ; i--) {
            a.addLast(i);
        }

    }


    public static void print(){
        System.out.println("----------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
