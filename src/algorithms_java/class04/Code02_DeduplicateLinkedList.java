package algorithms_java.class04;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_DeduplicateLinkedList {
    @Test
    public void testDeduplicateLinkList(){
        int time = 100;
        for(int i = 0; i < time; i++){
            Code01_ReverseList.Node head = Code01_ReverseList.getRandomLinkedList(10, 100);
            Code01_ReverseList.printLinkedList(head);
            System.out.println();
            Code01_ReverseList.Node node = DeduplicateLinkedList(head, 36);
            Code01_ReverseList.printLinkedList(node);
            System.out.println();
        }
    }

    public Code01_ReverseList.Node DeduplicateLinkedList(Code01_ReverseList.Node head, int value){
        Code01_ReverseList.Node prev = null;
        while(head != null){
            if(head.value != value){
                break;
            }
            prev = head;
            head = head.next;
        }

        Code01_ReverseList.Node cur = head;
        while(cur != null){
            if(cur.value == value){
                prev.next = cur.next;
                cur.next = null;
                cur = prev.next;
            }else{
                prev = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
