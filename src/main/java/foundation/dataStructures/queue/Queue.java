package foundation.dataStructures.queue;

public interface Queue <E>{

    //向队尾插入元素
    boolean offer(E value);
    //获取队头值，并移除
    E poll();
    //获取队头值，不移除
    E peek();
    //判断队列是否为空
    boolean isEmpty();
    //判断队列是否满
    boolean isFull();
}
