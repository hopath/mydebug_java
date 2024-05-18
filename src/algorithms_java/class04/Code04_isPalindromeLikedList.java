package algorithms_java.class04;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_isPalindromeLikedList {

    public static void main(String[] args) {
        Code03_LinkedListMid.Node test = new Code03_LinkedListMid.Node(1);
        test.next = new Code03_LinkedListMid.Node(2);
        test.next.next = new Code03_LinkedListMid.Node(3);
        test.next.next.next = new Code03_LinkedListMid.Node(4);
        test.next.next.next.next = new Code03_LinkedListMid.Node(3);
        test.next.next.next.next.next = new Code03_LinkedListMid.Node(2);
        test.next.next.next.next.next.next = new Code03_LinkedListMid.Node(1);
        //test.next.next.next.next.next.next.next = new Code03_LinkedListMid.Node(1);
        System.out.println(isPalindromeLikedList(test));
        Code03_LinkedListMid.printLinkedList(test);
    }
    public static boolean isPalindromeLikedListByStack(Code03_LinkedListMid.Node head){
        return true;
    }
    public static boolean isPalindromeLikedList(Code03_LinkedListMid.Node head){
        if(head == null || head.next == null || head.next.next == null){
            return true;
        }
        Code03_LinkedListMid.Node mid = Code03_LinkedListMid.getOddMidOrEvenLeftMid(head);
        Code03_LinkedListMid.Node pre = mid;
        Code03_LinkedListMid.Node cur = mid.next;
        Code03_LinkedListMid.Node next = null;
        mid.next = null;

        //反转链表
        while(cur != null){
            //起保存下一个节点的作用，关注于cur,pre若已经反转完成也就没必要保存了
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        Code03_LinkedListMid.Node last = pre;
        //从左向右，从右向左同时遍历，若遍历过程中左右对应值不同则返回false
        while(head != null){
            if(head.value != pre.value){
                //将链表调回去
                pre = last;
                next = last.next;
                last.next = null;
                while(pre != mid){
                    last = next;
                    next = last.next;
                    last.next = pre;
                    pre = last;
                }
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        //将链表调回去
        pre = last;
        next = last.next;
        last.next = null;
        while(pre != mid){
            last = next;
            next = last.next;
            last.next = pre;
            pre = last;
        }
        return true;
    }

    public static void insertEqualNode(Code03_LinkedListMid.Node head){
        Code03_LinkedListMid.Node mid = Code03_LinkedListMid.getOddMidOrEvenLeftMid(head);
        Code03_LinkedListMid.Node pre = mid;
        Code03_LinkedListMid.Node cur = mid.next;
        Code03_LinkedListMid.Node lNext = null;
        Code03_LinkedListMid.Node rNext = null;
        mid.next = null;

        //反转链表
        while(cur != null){
            //起保存下一个节点的作用，关注于cur,pre若已经反转完成也就没必要保存了
            lNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = lNext;
        }

        while(head != null && head != pre){
            lNext = head.next;
            rNext = pre.next;
            head.next = pre;
            pre.next = lNext;
            head = lNext;
            pre = rNext;
        }
    }
}
