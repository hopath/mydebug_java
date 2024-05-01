package algorithms_java.class05;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_QueueByConstantArray {
    public int arr[];
    public int begin;
    public int end;
    public int size;
    private int limit;

    public void MyQueue(int limit){
        arr = new int[limit];
        begin = -1;
        end = 0;
        size = 0;
        this.limit = limit;
    }

    private int nextInt(int pos){
        if(pos >= limit){
            pos = 0;
        }else {
            pos++;
        }
        return pos;
    }
    public void push(int value) throws Exception {
//        if(size < 5){
//            arr[++begin] = value;
//            size++;
//            if(begin >= limit){
//                begin = 0;
//            }
//        }else{
//            throw new Exception("栈已经满了...");
//        }
        if(size == limit){
            throw new Exception("队列已经满了...");
        }
        arr[begin] = value;
        begin = nextInt(begin);
        size++;
    }

    public int pop() throws Exception {
//        int top = 0;
//        if(size > 0){
//            top = arr[end];
//            end++;
//            size--;
//            if(end >= limit){
//                end = 0;
//            }
//            return top;
//        }else{
//            throw new Exception("栈已经空了...");
//        }
        if(size == 0){
            throw new Exception("队列已经空了...");
        }
        int ans = arr[end];
        end = nextInt(end);
        size--;
        return ans;
    }

}
