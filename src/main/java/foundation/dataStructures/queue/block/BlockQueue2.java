package foundation.dataStructures.queue.block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue2<E> implements BlockQueue<E> {
    private final E[] array;
    private int head,tail;
    private AtomicInteger size = new AtomicInteger();//原子类保证线程安全
   // private int size;
    public BlockQueue2(int capacity){
        array =(E[]) new Object[capacity];
    }

    //创建锁对象 锁head
    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();
    //创建锁对象 锁tail
    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();
    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c;
        try{
            while (isFull()){
                tailWaits.await();//进入阻塞队列
            }
            array[tail] = e;
            if(++tail==array.length){
                tail = 0;
            }
            c = size.getAndIncrement();//size++
            if(c + 1< array.length){
                tailWaits.signal();
            }

        }finally {
            tailLock.unlock();
        }
        //唤醒headWaits （唤醒操作只能在相同的锁里面才能用）
        //优化:减少加锁解锁的操作，通过级联通知的方式，offer线程只有在队列从0>>1的时候才会唤醒headWaits，其余唤醒交给poll线程自己唤醒
        if(c==0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        E value;
        int c;
        try{
            while (isEmpty()){
                headWaits.await();
            }
            value = array[head];
            if(++head==array.length){
                head = 0;
            }
            c = size.getAndDecrement();//size--
            if(c>1){
                headWaits.signal();
            }
        }finally {
            headLock.unlock();
        }

        //唤醒tailWaits
        //与前面类似，poll线程只负责在队列从满到不满的状态才会唤醒tailWaits，其余情况交给offer线程自己唤醒
        if(c==array.length) {
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }
        return value;
    }

    @Override
    public E poll(long timeout) throws InterruptedException {
       return null;
    }

    private boolean isEmpty(){
        return size.get()==0;
    }
    private boolean isFull(){
        return size.get()==array.length;
    }
}
