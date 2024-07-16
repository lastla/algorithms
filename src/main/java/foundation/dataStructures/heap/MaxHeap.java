package foundation.dataStructures.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxHeap {
    private int[] array;
    private int size;

    public MaxHeap(int capacity){
        array = new int[capacity];
    }

    public MaxHeap(int[] array){
        this.array =array;
        size = array.length;
    }
    //建堆
    private void heapify(){
        //找到最后一个非叶子节点，size/2 -1
        for (int i = size/2 -1; i >=0 ; i--) {
            down(i);
        }
    }

    //删除堆顶元素
    public int poll(){
        int value = array[0];
        swap(0,size-1);
        size--;
        down(0);
        return value;
    }

    //删除指定索引处的元素
    public int poll(int index){
        int value = array[index];
        swap(index,size-1);
        size--;
        down(index);
        return value;
    }
    //获取堆顶元素
    public int peek(){
        return array[0];
    }
    //添加元素
    public boolean offer(int offered){
        if(size>=array.length){
            return false;
        }
        up(offered);
        size++;
        return true;
    }
    //将inserted元素上浮，直到小于父元素或到堆顶
    private void up(int offered){
        int child = size;
        while (child>0){
            int parent = (child-1)/2;
            if(offered>array[parent]){
                array[child] = array[parent];
            }else{
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }
    //将parent处元素进行下潜，与两孩子中的较大者进行交换，直到没有孩子或孩子没它大
    private void down(int parent){

        int left = 2*parent +1;
        int right = left +1;
        int max = parent;
        if(left<size && array[left] > array[max]){
            max = left;
        }
        if(right<size && array[right] > array[max]){
            max =right;
        }
        if(max!=parent){
            swap(max,parent);
            down(max);
        }

    }

    //交换
    private void swap(int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    //堆排序
    public static void main(String[] args) {
        /*int[] array = {1,2,3,4,5,6,7};
        MaxHeap maxHeap = new MaxHeap(array);
        maxHeap.heapify();
        System.out.println(Arrays.toString(array));
        maxHeap.sort();
        System.out.println(Arrays.toString(array));*/
        PriorityQueue<Integer> heap = new PriorityQueue();
        heap.offer(9);
        heap.offer(4);
        heap.offer(5);
        heap.offer(3);
        heap.offer(8);
        heap.forEach(e->{
            System.out.println(e);
        });
        heap.poll();
    }
    public void sort(){
        int i = size;
        while(size>1){
            swap(0,size-1);
            size--;
            down(0);

        }
        size = i;
    }

}
