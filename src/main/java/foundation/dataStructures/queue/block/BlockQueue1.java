package foundation.dataStructures.queue.block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//单锁
public class BlockQueue1<E> implements BlockQueue<E> {

    private final E[] array;
    private int head,tail,size;
    public BlockQueue1(int capacity){
        array =(E[]) new Object[capacity];
    }

    //创建锁对象
    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();
    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (isFull()){
                tailWaits.await();//进入阻塞队列
            }
            array[tail] = e;
            if(++tail==array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();//唤醒等待非空队列的线程
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()){
                if(t<=0){
                    return false;
                }
                t = tailWaits.awaitNanos(t);//进入阻塞队列,并根据时间参数自动唤醒，返回值是等待剩余的时间
            }
            array[tail] = e;
            if(++tail==array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();//唤醒等待非空队列的线程
            return true;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (isEmpty()){
                headWaits.await();
            }
            E value = array[head];
            if(++head==array.length){
                head = 0;
            }
            size--;
            tailWaits.signal();
            return value;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public E poll(long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isEmpty()){
                if(t<=0){
                    return null;
                }
                t = headWaits.awaitNanos(t);
            }
            E value = array[head];
            if(++head==array.length){
                head=0;
            }
            size--;
            tailWaits.signal();
            return value;
        }finally {
            lock.unlock();
        }
    }

    private boolean isEmpty(){
        return size==0;
    }
    private boolean isFull(){
        return size==array.length;
    }
}
