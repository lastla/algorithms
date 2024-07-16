package foundation.dataStructures.queue;

import java.util.Iterator;
//单向环形链表实现
public class LinkedListQueue<E> implements Queue<E>,Iterable<E>{

    class Node<E>{
        E value;
        Node next;


        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    private Node<E> head = new Node<>(null,null);
    private Node<E> tail = head;
    private int size;//节点数
    private int capacity = Integer.MAX_VALUE;//队列的容量
    //java中构造方法内相同的代码可以抽取到初始化语句块内
    {
        tail.next = head;
    }
    public LinkedListQueue(int capacity){
        this.capacity = capacity;
    }
    public LinkedListQueue(){

    }
    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        Node<E> p = new Node<>(value,head);
        tail.next = p;
        tail = p;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        if(first==tail){
            tail=head;
        }
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return (E) head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E value = p.value;
                p=p.next;
                return value;
            }
        };
    }
}
