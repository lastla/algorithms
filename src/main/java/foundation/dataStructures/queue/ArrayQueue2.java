package foundation.dataStructures.queue;

import java.util.Iterator;
//仅使用头尾指针判断队满队空
public class ArrayQueue2<E> implements Queue<E>,Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;
    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        array[tail%array.length] = value;
       // array[(int)Integer.toUnsignedLong(tail)%array.length] = value; 将int类型转为unsigned long解决超过int最大值的问题
        /**
         *求模运算：
         * 求模的本质除法，而除以2^n次方可以进行移位运算，移位后的值为商，被移除的值为余数
         * 如果除数是2的n次方，那么被除数的后n位即为余数（模）
         * 求被除数后n位方法： 被除数 跟 2^n次方按位与
         */
       // array[tail&array.length-1] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
       E value = array[head%array.length];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return array[head%array.length];
    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    @Override
    public boolean isFull() {
        return tail-head==array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p!=tail;
            }

            @Override
            public E next() {
                E value = array[p%array.length];
                p++;
                return value;
            }
        };
    }
}
