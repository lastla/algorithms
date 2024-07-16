package foundation.dataStructures.heap;

import java.util.Arrays;

public class MinHeap {
    private int[] array;
    private int size;

    public MinHeap(int capacity){
        array = new int[capacity];
    }
    public MinHeap(int[] array){
        this.array = array;
        size = array.length;
        heapify();
    }
    public int poll(){
        int value = array[0];
        swap(0,size-1);
        size--;
        down(0);
        return value;
    }

    public int poll(int index){
        int value = array[index];
        swap(index,size-1);
        size--;
        down(index);
        return value;
    }

    public int peek(){
        return array[0];
    }

    public boolean offer(int offered){
        if(size==array.length){
            return false;
        }
        up(offered);
        size++;
        return true;
    }
    private void up(int offered){
        int child = size;
        while (child>0){
            int parent = (child-1)/2;
            if(offered < array[parent]){
                swap(parent,child);
                child = parent;
            }else{
                break;
            }
        }
        array[child] = offered;
    }
    private void heapify(){
        for (int i = size/2 -1; i >= 0 ; i--) {
            down(i);
        }
    }
    private void down(int parent){
        int left = 2 * parent +1;
        int right = left +1;
        int min = parent;
        if(left<size && array[left] < array[min]){
            min = left;
        }
        if(right<size && array[right]<array[min]){
            min = right;
        }
        if(min != parent){
            swap(parent,min);
            down(min);
        }
    }

    private void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void sort(){
        int i = size;
        while (size>1){
               swap(0,size-1);
               size--;
               down(0);
        }
        size = i;
    }

    public static void main(String[] args) {
       /* int[] array = {7,6,5,4,3,2,1};
        MinHeap minHeap = new MinHeap(array);
        System.out.println(minHeap.offer(0));
        System.out.println(Arrays.toString(array));
        System.out.println("--------------------------");
        System.out.println(minHeap.poll());
        System.out.println(Arrays.toString(array));
        System.out.println("--------------------------");
        *//*System.out.println(minHeap.poll(3));
        System.out.println(Arrays.toString(array));
        System.out.println("--------------------------");*//*
        System.out.println(minHeap.offer(0));
        System.out.println(Arrays.toString(array));
        System.out.println("--------------------------");
        minHeap.sort();
        System.out.println(Arrays.toString(array));*/
        int[] nums = {-1,-1};
        System.out.println(findKthLargest(nums, 2));

    }
    static int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(k);

        for (int i = 0; i <k ; i++) {
            heap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if(nums[i]>=heap.peek()){
                heap.poll();

                heap.offer(nums[i]);
            }
        }
        return heap.peek();
    }

}
