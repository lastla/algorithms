package foundation.dataStructures.queue.priority;

public class MinHeap {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    private ListNode[] array;
    private int size;
    public MinHeap(int capacity){
        array = new ListNode[capacity];
    }

    public boolean offer(ListNode offered){
        if(isFull()){
            return false;
        }
        int child = size;
        size++;
        int parent = (child-1)/2;
        while(child>0 && offered.val<array[parent].val){
            array[child] = array[parent];
            child = parent;
            parent = (child-1)/2;
        }
        array[child] = offered;
        return true;
    }
    public ListNode poll() {
        if(isEmpty()){
            return null;
        }
        //交换根节点与末尾节点
        swap(0,size-1);
        size--;
        ListNode value = array[size];
        array[size] = null;//help GC
        //将根节点值下潜
        down(0);
        return value;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int min = parent;
        if (left < size && array[left].val < array[min].val) {
            min = left;
        }
        if (right < size && array[right].val < array[min].val) {
            min = right;
        }
        if (min != parent) {
            swap(min, parent);
            down(min);
        }
    }
    public void swap(int i,int j){
        ListNode temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    public boolean isEmpty(){
        return size==0;
    }
    public boolean isFull(){
        return size==array.length;
    }

}
