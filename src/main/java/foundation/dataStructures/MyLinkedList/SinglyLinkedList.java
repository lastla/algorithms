package foundation.dataStructures.MyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedList implements Iterable<Integer> {
        private Node head;



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
        head = new Node(value,head);
    }
    //尾插法
    public void addLast(int value){
        Node last = findLast();
        if(last==null){
            addFirst(value);
        }
        else {
            last.next = new Node(value,null);
        }

    }
    private Node findLast(){
        Node temp = head;
        if(head==null){
            return null;
        }

        while (temp.next!=null){
        temp=temp.next;
        }
        return temp;
    }

    //遍历链表1.while
    public void loop(){
        Node temp = head;
        while (temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }
    }
    //遍历链表2.lambda
    public void loop2(Consumer consumer){
        Node temp = head;
        while (temp!=null) {

            consumer.accept(temp.value);
            temp = temp.next;
        }

    }
    //遍历链表3.iterable
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node temp = head;
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
    //递归
    public void loop3(Consumer before,Consumer after){
        recursion(head,before,after);
    }
    public void recursion(Node curr, Consumer before, Consumer after){
        if(curr==null){
            return;
        }
        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);
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
        int i=0;
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
        if(index==0){
            addFirst(value);
            return;
        }
        Node prev = findNode(index - 1);//找到待插入索引的上一个节点
        if(prev==null){
           throw  illegalIndex(index);
        }
        prev.next = new Node(value,prev.next);
    }
    //删除第一个
    public void removeFirst(){
        if(head==null){
            illegalIndex(0);
        }
        head = head.next;
    }
    //根据索引删除元素
    public void remove(int index){
        if(index==0){
            removeFirst();
            return;
        }
        Node prev = findNode(index-1);
        if(prev==null || prev.next==null ){
           throw  illegalIndex(index);

        }
        prev.next = prev.next.next;
    }
    //反转链表
    public  Node reverse(Node o1){
        if(o1==null){
            return null;
        }
        Node o2 = o1.next;
        Node n1 = o1;

        while (o2!=null){
            o1.next=o2.next;
            Node temp = n1;
            n1 = o2;
            n1.next=temp;
            o2=o1.next;
        }
        return n1;
    }

}
