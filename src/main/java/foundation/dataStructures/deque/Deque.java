package foundation.dataStructures.deque;

/**
 * 双端队列，可以操作头尾的添加和删除
 * double ended queue
 */
public interface Deque<E> {

    boolean offerFirst(E e);

    boolean offerLast(E e);

    E pollFirst();

    E pollLast();

    E peekFirst();

    E peekLast();

    boolean isEmpty();

    boolean isFull();
}