package algorithms_java.class04;

import java.util.HashMap;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code06_CopyListWithRandom {
    public static void main(String[] args) {
        RNode rNode = new RNode(3);
        RNode tmp = rNode;
        rNode.random = rNode;
        rNode.next = new RNode(2);
        rNode.next.random = rNode.next;
        rNode.next.next = new RNode(1);
        rNode.next.next.random = rNode.next.next;
        printLinkedList(rNode);
        System.out.println();
        System.out.println(tmp.random.value);
        System.out.println(tmp.next.random.value);
        //System.out.println(tmp.next.next);
        System.out.println(tmp.next.next.random.value);
        RNode copy = copyListWithRandom01(rNode);
        printLinkedList(copy);
        System.out.println();
        System.out.println(copy.random.value);
        System.out.println(copy.next.random.value);
        //System.out.println(copy.next.next);
        System.out.println(copy.next.next.random.value);
    }
    public static void printLinkedList(RNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }
    static class RNode{
        int value;
        RNode next;
        RNode random;

        public RNode(int value) {
            this.value = value;
        }

        public RNode(int value, RNode next, RNode random) {
            this.value = value;
            this.next = next;
            this.random = random;
        }
    }

    public static RNode copyListWithRandom01(RNode head){
        RNode copy = new RNode(head.value, head.next, head.random);
        head = head.next;
        RNode cur = copy;

        while(head != null){
           cur.next = new RNode(head.value, head.next, head.random);
           cur = cur.next;
           head = head.next;
        }

        return copy;
    }
    public static RNode copyListWithRandom02(RNode head){
        RNode cur = head;
        RNode next = null;

        while(cur != null){
            next = cur.next;
            cur.next = new RNode(cur.value);
            cur.next.next = next;
            cur = next;
        }

        //解决random指向问题
        cur = head;
        while(cur != null){
            RNode left = cur;
            RNode right = cur.next;

            if(left.random != null){
                right.random = left.random.next;
            }else {
                right.random = null;
            }
            cur = cur.next.next;
        }

        //next连接
        RNode res = head.next;
        cur = head.next;
        while(cur.next != null){
            next = cur.next;
            cur.next = cur.next.next;
            cur = next;
        }

        return res;
    }


    public static RNode copyListWithRandom03(RNode head){
        HashMap<RNode, RNode> nodeHashMap = new HashMap<>();
        RNode cur = head;

        while(cur != null){
            nodeHashMap.put(cur, new RNode(cur.value));
            cur = cur.next;
        }

        cur = head;
        while(cur != null){
            RNode ncur = nodeHashMap.get(cur);
            ncur.next = nodeHashMap.get(cur.next);
            ncur.random = nodeHashMap.get(cur.random);
            cur = cur.next;
        }

        return nodeHashMap.get(head);

    }

}
