package foundation.dataStructures.MyArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer>{
    private int size = 0;
    private int capacity = 10;
    private int[] array = {};

    //在数组尾部插入数据
    public boolean addLast(int element){
        return add(size,element);
    }
    //根据索引插入元素
    public boolean add(int index,int element) {
        checkAndGrow();
        if(index >= 0 && index <size) {
            System.arraycopy(array,index,array,index+1,size-index);
        }
        array[index] = element;
        size++;
        return true;
    }

    private void checkAndGrow() {
        //容量检查
        if(size==0){
            array = new int[capacity];
        }
        else if(size==capacity){
            //进行扩容
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array,0,newArray,0,size);
            array = newArray;
        }
    }

    //根据索引查询元素
    public int get(int index) {

        return array[index];
    }
    //根据索引修改元素

    public boolean update(int index,int element){
            array[index - 1] = element;
            return true;
    }
    //根据索引删除元素 
    public int remove(int index){
        int temp = array[index];
        if(index>0 && index<size){
            System.arraycopy(array,index+1,array,index,size-index-1);
        }
        size--;
        return temp;
    }

    //遍历元素，做一个类似stream foreach

    public void foreach(Consumer<Integer> consumer){
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);
        }
    }


    //遍历迭代器遍历
    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {//hasNext,判断下个元素是否存在
            int i = 0;
            @Override
            public boolean hasNext() {
                return i<size;
            }

            @Override
            public Integer next() {//取出当前元素1
                return array[i++];
            }
        };
    }
    //stream遍历
    public IntStream stream(){
        //return IntStream.of(array);//会把无效部分也遍历出来
        return IntStream.of(Arrays.copyOfRange(array,0,size));//根据索引拷贝数组，拷贝范围是[0,size)
    }

}
