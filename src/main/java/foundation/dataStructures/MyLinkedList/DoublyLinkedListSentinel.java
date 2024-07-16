package foundation.dataStructures.MyLinkedList;


import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Iterator;

public class DoublyLinkedListSentinel implements Iterable<Integer> {

    private static class Node{
        Node prev;//前驱节点
        int value;
        Node next;//后继节点


        public Node(Node prev,int value,Node next){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node head ;
    private Node tail;
    public DoublyLinkedListSentinel(){
        head = new Node(null,666,null);
        tail = new Node(null,888,null);
        head.next = tail;
        tail.prev = head;
    }
    private static IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法", index));
    }
    //根据索引找双向链表的节点
    private Node findNode(int index){
        int i = -1;//包含有哨兵节点所以从-1开始
        for (Node p = head; p!=tail; p=p.next, i++){
            if(i==index){
                return p;
            }
        }
        return null;
    }
    //根据索引删除元素
    public void insert(int index, int value){
        Node prev = findNode(index - 1);
        if(prev==null){
            throw illegalIndex(index);
        }
        Node next = prev.next;
        Node node = new Node(prev, value, next);
        prev.next = node;
        next.prev = head;
    }
    //头插
    public void addFirst(int value){
        insert(0,value);
    }
    //根据索引删除元素
    public void remove(int index){
        Node prev = findNode(index-1);
        if(prev==null){
          throw illegalIndex(index);
        }
        Node removed = prev.next;
        if(removed == tail){
            throw illegalIndex(index);
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;

    }
    //removeLast
    public void removeLast(){
        Node removed = tail.prev;
        if(removed==head){
            throw illegalIndex(0);
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;
    }
    //addLast
    public void addLast(int value){
        Node prev = tail.prev;
        Node inserted = new Node(prev, value, tail);
        prev.next = inserted;
        tail.prev = inserted;

    }
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;
            @Override
            public boolean hasNext() {
                return p!=tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

}
