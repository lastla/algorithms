package foundation.dataStructures.deque;

import java.util.Iterator;

/**
 * 仅用两个指针基于循环数组实现： tail所在位置不存值，即会浪费一个空间用来判空
 * 队尾添加元素时，先添加元素 再tail++
 * 对头添加元素时，先++ 再添加元素
 * @param <E>
 */
public class ArrayDeque<E> implements Deque<E>,Iterable<E> {
    /**
     * 修正tail++索引错误的情况
     * @param i 索引
     * @param length 数组长度
     * @return
     */
    static int inc(int i,int length){
        if(i+1>=length){
            return 0;
        }
        return i+1;
    }

    /**
     * 修正索引减一时出现的错误
     * @param i
     * @param length
     * @return
     */
    static int dec(int i,int length){
        if(i-1<0){
            return length-1;
        }
        return i-1;
    }

    E[] array;
    int head;
    int tail;
    public ArrayDeque(int capacity){
        array = (E[]) new Object[capacity+1];
    }


    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
         head = dec(head,array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()){
            return false;
        }
        array[tail] = e;
        tail = inc(tail,array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        E value = array[head];
        array[head] = null;//help GC
        head = inc(head,array.length);
        return value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()){
            return null;
        }
        tail = dec(tail,array.length);
        E value = array[tail];
        array[tail] = null;
        return value;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()){
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()){
            return null;
        }
        return array[dec(tail,array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    @Override
    public boolean isFull() {
        if(head<tail){
            return tail-head==array.length-1;
        } else if (head>tail) {
            return head - tail == 1;
        }
        return false;
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
                E value = array[p];
                p = inc(p,array.length);
                return value;
            }
        };
    }
}
