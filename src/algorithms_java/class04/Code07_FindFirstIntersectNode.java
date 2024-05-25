package algorithms_java.class04;

import java.util.HashSet;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_FindFirstIntersectNode {

    public static void main(String[] args) {

    }
    static class Node{
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    
    public static Node findFirstIntersectNode01(Node head){
        HashSet<Node> nodes = new HashSet<>();
        while(head != null){
            if(!nodes.contains(head)){
                nodes.add(head);
            }else {
                return head;
            }
        }

        return null;
    }

    public static Node findFistIntersectNode02(Node head){
        if(head.next == null){
            return head;
        }
        Node s = head;
        Node f = head;

        while(f != null){
            s = s.next;
            f = f.next.next;
            if(s == f){
                f = head;
                while(f != s){
                    s = s.next;
                    f = f.next.next;
                }
                return s;
            }
        }

        return null;
    }
}
