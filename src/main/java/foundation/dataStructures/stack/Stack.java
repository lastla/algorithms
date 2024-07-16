package foundation.dataStructures.stack;

public interface Stack <E>{
    //向栈顶压入元素
    boolean push(E value);
    //从栈顶弹出元素
    E pop();
    //返回栈顶元素，不弹出
    E peek();
    //判断栈是否为空
    boolean isEmpty();
    //判断栈是否满
    boolean isFull();
}
