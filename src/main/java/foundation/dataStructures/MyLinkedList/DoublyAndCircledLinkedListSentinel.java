package foundation.dataStructures.MyLinkedList;


import java.util.Iterator;

public class DoublyAndCircledLinkedListSentinel implements Iterable<Integer>{



    private static class Node{
        Node prev;
        int value;
        Node next;
        public Node(Node prev,int value,Node next){
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
    private Node sentinel = new Node(null,-1,null);//哨兵
    //初始化，是哨兵节点的头尾都指向自己
    public DoublyAndCircledLinkedListSentinel(){
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    //addFirst  a前驱节点，b后继节点
    public void addFirst(int value){
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a,value,b);
        a.next = added;
        b.prev = added;
    }
    //addLast
    public void addLast(int value){
        Node a = sentinel.prev;
        Node b = sentinel;
        Node added = new Node(a,value,b);
        a.next = added;
        b.prev = added;
    }
    //removeFirst
    public void removeFirst(){
        Node removed = sentinel.next;
        if(removed==sentinel){
            throw new IllegalArgumentException("不能删除哨兵节点");
        }
        Node a = sentinel;
        Node b = removed.next;
        a.next=b;
        b.prev = a;
    }

    //removeLast
    public void removeLast(){
        Node removed = sentinel.prev;
        if(removed==sentinel){
            throw new IllegalArgumentException("不能删除哨兵节点");
        }
        Node a = removed.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;
    }
    //remove by value
    public void removedByValue(int value){
        Node removed = findNode(value);
        if(removed==null){
            System.out.println("链表中没有这个元素");
        }
        Node a = removed.prev;
        Node b =removed.next;
        a.next = b;
        b.prev = a;
    }

    public Node findNode(int value){
        Node p = sentinel.next;
        while (p!=sentinel){
            if (p.value==value) {
                return p;
            }
            p=p.next;
        }
        return null;
    }

    //递归遍历
    public void loop1(){
        recursion(sentinel.next);
    }
    public void recursion(Node curr){

        if(curr==sentinel){
            return;
        }
        System.out.println(curr.value);
        recursion(curr.next);

    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;
            @Override
            public boolean hasNext() {
                return p!=sentinel;
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
