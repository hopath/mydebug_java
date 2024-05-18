package algorithms_java.class04;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_SmallerEqualBigger {
    public static void main(String[] args) {
        Code03_LinkedListMid.Node test = new Code03_LinkedListMid.Node(4);
        test.next = new Code03_LinkedListMid.Node(2);
        test.next.next = new Code03_LinkedListMid.Node(8);
        test.next.next.next = new Code03_LinkedListMid.Node(0);
        test.next.next.next.next = new Code03_LinkedListMid.Node(3);
        test.next.next.next.next.next = new Code03_LinkedListMid.Node(4);
        test.next.next.next.next.next.next = new Code03_LinkedListMid.Node(5);
        test.next.next.next.next.next.next.next = new Code03_LinkedListMid.Node(10);
            Code03_LinkedListMid.Node node = partitionLinkedList(test, 1);
            Code03_LinkedListMid.printLinkedList(node);
    }

    public static Code03_LinkedListMid.Node partitionLinkedList(Code03_LinkedListMid.Node head, int value) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Code03_LinkedListMid.Node smallHead = null;
        Code03_LinkedListMid.Node smallTail = null;
        Code03_LinkedListMid.Node equalHead = null;
        Code03_LinkedListMid.Node equalTail = null;
        Code03_LinkedListMid.Node bigHead = null;
        Code03_LinkedListMid.Node bigTail = null;

        while (head != null) {
            Code03_LinkedListMid.Node tmp = head.next;
            if (head.value == value) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else if (head.value > value) {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            } else {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            }
            head = tmp;
        }

//        if(smallHead == null && equalHead == null){
//            return bigHead;
//        }
//        if(smallHead == null && equalHead != null){
//            equalHead.next = bigHead;
//            return equalHead;
//        }
//        if(smallHead != null && equalHead == null){
//            smallHead.next = bigHead;
//            return smallHead;
//        }
//
//        smallTail.next = equalHead;
//        equalTail.next = bigHead;
//        return smallHead;
        if(smallTail != null){
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if(equalTail != null){
            equalTail.next = bigHead;
        }

        return smallTail != null ? smallHead : (equalTail == null ? bigHead : equalHead);
    }
}
