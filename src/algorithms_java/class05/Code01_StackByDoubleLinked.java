package algorithms_java.class05;



/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_StackByDoubleLinked<T> {
    private DoubleNode head;

    private class DoubleNode{
        public T value;
        public DoubleNode prev;
        public DoubleNode next;

        public DoubleNode(T data){
            value = data;
        }
    }

    public void MyStack(){
        head = null;
    }

    @org.junit.jupiter.api.Test
    public void testCode01() throws Exception {
        Code01_StackByDoubleLinked code1_stackByDoubleLinked = new Code01_StackByDoubleLinked();
        code1_stackByDoubleLinked.MyStack();
        int rad = (int)(Math.random() * 100) + 1;
        System.out.println(rad);
        for(int i = 0; i < rad; i++){
            code1_stackByDoubleLinked.push(i);
        }
        for(int i = 0; i < rad + 1; i++){
            code1_stackByDoubleLinked.pop();
        }
    }

    public void push(T value){
        if(head == null){
            head = new DoubleNode(value);
        }else{
            DoubleNode next = new DoubleNode(value);
            head.next = next;
            next.prev = head;
            head = next;
        }
    }

        public T pop() throws Exception {
        if(head == null){
            throw new Exception("栈是空的...");
        }
        T ans = head.value;
        DoubleNode prev = head.prev;
        if(prev != null){
            head.prev = null;
            prev.next = null;
            head = prev;
        }
        else {
            head = null;
        }
        return ans;
    }
}
