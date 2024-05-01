package algorithms_java.class05;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_StackByArray {
    private Object arr[];
    private int capacity;
    private int size;

    public Code02_StackByArray() {
        this.capacity = 10;
        this.size = 0;
        this.arr = new Object[capacity];
    }

    @Test
    public void testCode02(){
        Code02_StackByArray code2_stackByArray = new Code02_StackByArray();
        for(int i = 0; i < 16; i++){
            code2_stackByArray.push(i);
        }
        for(int i = 0; i < 16; i++){
            System.out.print(code2_stackByArray.pop() + " ");
        }
    }

    private void expanse(){
        if(size == capacity){
            capacity += 4;
            Object tmp[] = new Object[capacity];
            for(int i = 0; i < size; i++){
                tmp[i] = arr[i];
            }
            arr = tmp;
            System.out.println("增容成功...");
        }
    }
    public void push(Object value){
        expanse();
        arr[size] = value;
        size++;
    }
    public Object pop(){
        size--;
        return arr[size];
    }
}