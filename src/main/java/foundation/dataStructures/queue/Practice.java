package foundation.dataStructures.queue;

public class Practice<E> implements Queue<E>{

    private E[] array;
    private int head = 0;
    private int tail = 0;
    private int capacity=0;
    private int size = 0;
    public Practice(int capacity){
        this.capacity = capacity;
        array =(E[]) new Object[capacity];
    }
    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        array[tail] = value;
        tail = (tail +1)%array.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E value = array[head];
        head = (head+1)%array.length;
        size--;
        return value;
    }

    @Override
    public E peek() {
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }
}
