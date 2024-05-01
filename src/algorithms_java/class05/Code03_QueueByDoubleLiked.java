package algorithms_java.class05;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_QueueByDoubleLiked<T>{
    private DoubleNode head;
    private DoubleNode last;

    private class DoubleNode{
        public T value;
        public DoubleNode prev;
        public DoubleNode next;

        public DoubleNode(T data){
            value = data;
        }
    }

    @Test
    public void testCodeO3() throws Exception {
        Code03_QueueByDoubleLiked<Integer> Code3_queueByDoubleLiked = new Code03_QueueByDoubleLiked<>();
        for(int i = 0; i < 10; i++){
            Code3_queueByDoubleLiked.push(i);
        }
        for (int i = 0; i < 11; i++) {
            System.out.print(Code3_queueByDoubleLiked.pop() + " ");
        }

    }

    public void MyQueue() {
       head = null;
    }

    public void push(T value){
        if(head == null){
            head = new DoubleNode(value);
            last = head;
        }else{
            DoubleNode next = new DoubleNode(value);
            last.next = next;
            next.prev = last;
            last = next;
        }
    }

    public T pop() throws Exception {
        if(head == null){
            throw new Exception("队列已经空了...");
        }

        DoubleNode prev = head;

        if(prev.next == null){
            head = null;
        }else{
            head = head.next;
            prev.next = null;
            head.prev = null;
        }

        return prev.value;
    }

    public void printLinkedList(DoubleNode head){
        while(head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

}
