package algorithms_java.class04;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_LinkedListMid {

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node test = new Node(1);
        Node cur = test;
        for (int i = 2; i < 5; i++) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        //System.out.println(getOddMidOrEvenLeftMid(test));
        //System.out.println(getOddMidOrEvenRightMid(test));
        //System.out.println(getOddLeftMidOrEvenPreLeftMid(test));
        System.out.println(getOddLeftMidOrEvenPreRightMid(test));
        printLinkedList(test);
    }

    public static Node getOddMidOrEvenLeftMid(Node head) {
        int cnt = 0;

        Node cur = head;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }

        if (cnt % 2 == 0) {
            return getEvenLinkedListLeftMid(head);
        }

        return getOddLinkedListMid(head);
    }

    public static Node getOddMidOrEvenRightMid(Node head) {
        int cnt = 0;

        Node cur = head;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }

        if (cnt % 2 == 0) {
            return getEvenLinkedListRightMid(head);
        }

        return getOddLinkedListMid(head);
    }

    public static Node getOddLeftMidOrEvenPreLeftMid(Node head) {
        int cnt = 0;

        Node cur = head;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }

        if (cnt % 2 == 0) {
            return getEvenLinkedListPreLeftMid(head);
        }

        return getOddLinkedListLeftMid(head);
    }

    public static Node getOddLeftMidOrEvenPreRightMid(Node head) {
        int cnt = 0;

        Node cur = head;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }

        if (cnt % 2 == 0) {
            return getEvenLinkedListPreRightMid(head);
        }

        return getOddLinkedListLeftMid(head);
    }

    public static Node getOddLinkedListMid(Node head) {
        if (head.next == null || head == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node getEvenLinkedListLeftMid(Node head) {
        if (head.next.next == null || head == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node getEvenLinkedListRightMid(Node head) {
        if (head.next.next == null) {
            return head.next;
        }
        if (head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node getOddLinkedListLeftMid(Node head) {
        if (head.next == null || head == null) {
            return null;
        }

        Node pre = null;
        Node slow = head;
        Node fast = head;

        while (fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return pre;
    }

    public static Node getEvenLinkedListPreLeftMid(Node head) {
        if (head.next.next == null || head == null) {
            return null;
        }

        Node pre = null;
        Node slow = head;
        Node fast = head;

        while (fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return pre;
    }

    public static Node getEvenLinkedListPreRightMid(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }

        Node pre = null;
        Node slow = head;
        Node fast = head;

        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return pre;
    }

}
