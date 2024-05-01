package algorithms_java.class01;

import java.util.ArrayList;

public class Code01_ReverseList {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            value = data;
        }
    }

    public static void main(String[] args) {
        //用对数器进行测试
        int time = 1000;
        int i = 0;
        for(; i < time; i++){
            Node head = getRandomLinkedList(100, 100);
            printLinkedList(head);
            System.out.println();
            Node rev2 = Code01_ReverseList.reverseLinkedList(head);
            printLinkedList(rev2);
            Code01_ReverseList.reverseLinkedList(rev2);
            System.out.println();
            Node rev1 = Code01_ReverseList.testReverseLinkedList(head);
            printLinkedList(rev1);
            System.out.println();

            if(!(isSame(rev1, rev2))){
                System.out.println("no!!!!!");
                break;
            }
        }
        if(i == time){
            System.out.println("yes!!!!!");
        }
    }

    //单链表反转
    //通过若干个指针实行链表的反转
    public static Node reverseLinkedList(Node head){
        Node prev = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    //使用ArrayList实现了链表反转，作为测试
    public static Node testReverseLinkedList(Node head){
        if(head == null){
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int N = list.size();
        for(int i = 1; i < N; i++){
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);
    }

    //判读用两种不同方法跑出来的结果是否相同
    public static boolean isSame(Node r1, Node r2){
        while(r1 != null){
            if(r1.value != r2.value){
                return false;
            }
            r1 = r1.next;
            r2 = r2.next;
        }
        return true;
    }

    //打印单链表
    public static void printLinkedList(Node head){
        while(head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    //生成随机单链表
    public static Node getRandomLinkedList(int maxSize, int maxValue){
        int size = (int)(Math.random() * maxSize) + 1;
        int value = (int)(Math.random() * maxValue) + 1;
        Node head = new Node(value);
        Node cur = head;

        for(int i = 1; i < size; i++){
            value = (int)(Math.random() * maxValue) + 1;
            cur.next = new Node(value);
            cur = cur.next;
        }
        return head;
    }
}

