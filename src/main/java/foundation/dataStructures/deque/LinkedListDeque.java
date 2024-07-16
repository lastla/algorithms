package foundation.dataStructures.deque;

import java.util.Iterator;

/**
 * 基于双向环形链表带哨兵实现  reason：方便删除尾
 * @param <E>
 */
public class LinkedListDeque<E> implements Deque<E>,Iterable<E> {

    class Node<E>{
        Node<E> prev;
        E value;
        Node<E> next;
        public Node(E value,Node<E> prev,Node<E> next){
            this.value=value;
            this.next=next;
            this.prev=prev;
        }
    }

    private int capacity;
    private int size;
    private Node<E> sentinel = new Node<>(null,null,null);
    public LinkedListDeque(int capacity){
            this.capacity=capacity;
            sentinel.next=sentinel;
            sentinel.prev=sentinel;
    }
    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(e,a,b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(isFull()){
            return false;
        }
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(e,a,b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        Node<E> removed = sentinel.next;
        Node<E> b = removed.next;
        sentinel.next = removed.next;
        b.prev=sentinel;
        size--;
        return removed.value;
    }

    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        Node<E> removed = sentinel.prev;
        Node<E> b = removed.prev;
        b.next  = sentinel;
        sentinel.prev = b;
        size--;
        return removed.value;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()){
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if(isEmpty()){
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;
            @Override
            public boolean hasNext() {
                return p!=sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
