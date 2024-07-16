package foundation.dataStructures.queue.block;

public interface BlockQueue<E> {//阻塞队列

    void offer(E e) throws InterruptedException;
    boolean offer(E e,long timeout) throws InterruptedException;

    E poll() throws InterruptedException;
    E poll(long timeout) throws InterruptedException;
}
