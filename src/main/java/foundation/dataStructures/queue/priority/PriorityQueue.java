package foundation.dataStructures.queue.priority;

import foundation.dataStructures.queue.Queue;

/**
 * 使用大顶堆实现优先级队列：
 *      -- 已知子节点求父节点 （i-1）/2 向下取整  i>0
 *      -- 已知父节点求子节点  左子节点：(2*i) +1 右子节点：（2*i）+2 结果必须<数组长度
 * @param <E>
 */
public class PriorityQueue<E extends Priority> implements Queue<E> {
    Priority[] array ;
    int size;
    public PriorityQueue(int capacity){
        array = new Priority[capacity];
    }

    /**
     * 待加入节点统一添加到数组末尾然后于父节点进行比较，如果父节点大则将父节点与offered交换，不断循环直到该节点=0,或该节点优先级小于父节点
     * @param offered
     * @return
     */
    @Override
    public boolean offer(E offered) {
        if(isFull()){
            return false;
        }
        int child = size;
        size++;
        int parent = (child-1)/2;
        while(child>0 && offered.priority()>array[parent].priority()){
            array[child] = array[parent];
            child = parent;
            parent = (child-1)/2;
        }
        array[child] = offered;
        return true;
    }

    /**
     * 首先将根节点与最后一个节点交互然后删除最后一个节点（数组尾部删除性能好）
     * 将根节点不断与他的两个子节点比较并与最大的那个子节点交换，直到没有子节点为止
     * @return
     */
    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        //交换根节点与末尾节点
        swap(0,size-1);
        size--;
        Priority value = array[size];
        array[size] = null;//help GC
        //将根节点值下潜
        down(0);
        return (E) value;
    }

    public void down(int parent){
        int left = 2*parent +1;
        int right = left +1;
        int max = parent;
        if(left<array.length && array[left].priority()>array[max].priority()){
            max = left;
        }
        if(right<array.length && array[right].priority()>array[max].priority()){
            max = right;
        }
        if(max!=parent){
            swap(parent,max);
            down(max);
        }
    }
    public void swap(int i,int j){
        Priority temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
    @Override
    public E peek() {
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size==array.length;
    }
}
