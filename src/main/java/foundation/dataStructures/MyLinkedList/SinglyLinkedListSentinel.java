package foundation.dataStructures.MyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;
//单向链表，带哨兵
public class SinglyLinkedListSentinel implements Iterable<Integer> {
        private Node head = new Node(666,null);



    private static class Node{
        int value;
        Node next;
        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    //头插法
    public void addFirst(int value){
        //head = new Node(value,head);
        insert(0,value);
    }
    //尾插法
    public void addLast(int value){
        Node last = findLast();
            last.next = new Node(value,null);

    }
    private Node findLast(){
        Node temp = head;
        while (temp.next!=null){
        temp=temp.next;
        }
        return temp;
    }

    //遍历链表1.while
    public void loop(){
        Node temp = head.next;
        while (temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }
    }
    //遍历链表2.lambda
    public void loop2(Consumer consumer){
        Node temp = head.next;
        while (temp!=null) {

            consumer.accept(temp.value);
            temp = temp.next;
        }

    }
    //遍历链表3.iterable
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node temp = head.next;
            @Override
            public boolean hasNext() {
                return temp!=null;
            }

            @Override
            public Integer next() {
                int value = temp.value;
                temp=temp.next;
                return value;
            }
        };
    }
    //根据索引获取值
    public int get(int index){
        Node node = findNode(index);
        if(node==null){//没找到的情况，抛出异常
            throw illegalIndex(index);
        }
        return node.value;
    }

    private static IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法", index));
    }

    private Node findNode(int index){
        Node temp = head;
        int i=-1;
        while (temp!=null){
            if(i==index){
                return temp;
            }
            temp=temp.next;
            i++;
        }
        return null;
    }
    //根据索引插入元素
    public void insert(int index, int value){

        Node prev = findNode(index - 1);//找到待插入索引的上一个节点
        if(prev==null){
           throw  illegalIndex(index);
        }
        prev.next = new Node(value,prev.next);
    }
    //删除第一个
    public void removeFirst(){
        remove(0);
    }
    //根据索引删除元素
    public void remove(int index){

        Node prev = findNode(index-1);
        if(prev==null || prev.next==null ){
           throw  illegalIndex(index);

        }
        prev.next = prev.next.next;
    }

}
