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

    
    public static Node isLoop01(Node head){
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

    /*
        若无环则返回null, 有环则返回入环第一个节点
     */
    public static Node isLoop02(Node head){
        if(head.next == null){
            return null;
        }
        Node s = head;
        Node f = head;

        while(f.next != null && f != null){
            s = s.next;
            f = f.next.next;
            if(s == f){
                f = head;
                while(f != s){
                    s = s.next;
                    f = f.next;
                }
                return s;
            }
        }

        return null;
    }

    public static Node isLoop03(Node head){
        if(head == null || head.next == null){
            return null;
        }
        Node s = head.next;
        Node f = head.next.next;

        while(s != f){
            if(f == null || f.next == null){
                return  null;
            }
            s = s.next;
            f = f.next.next;
        }

        f = head;

        while(s != f){
            s = s.next;
            f = f.next;
        }

        return f;
    }

    /*
        若相交返回第一个相交节点，否则返回null
     */
    public static Node noLoop(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        int len1 = 1;   int len2 = 1;
        Node cur1 = head1;  Node cur2 = head2;

        while(cur1.next != null){
            len1++;
            cur1 = cur1.next;
        }

        while (cur2.next != null){
            len2++;
            cur2 = cur2.next;
        }

        if(cur1 != cur2){
            return null;
        }

        if(cur1 == cur2){
            int dv = len1 - len2 > 0 ? len1 - len2 : len2 - len1;
            if(len1 > len2){
                for(int i = 0; i < dv; i++){
                    head1 = head1.next;
                }
            }else{
                for(int i = 0; i < dv; i++){
                    head2 = head2.next;
                }
            }

            while(head1 != null){
                if(head1 == head2){
                    return head1;
                }
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        return null;
    }

    public static Node noLoop02(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        //两条链表差值数
        int n = 0;
        Node cur1 = head1;  Node cur2 = head2;

        while(cur1.next != null){
            n++;
            cur1 = cur1.next;
        }

        while(cur2.next != null){
            n--;
            cur2 = cur2.next;
        }

        if(cur1 != cur2){
            return null;
        }

        //重定向较长的链表,根据差值的正负
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n = Math.abs(n);

        for(int i = 0; i < n; i++){
            cur1 =  cur1.next;
        }

        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;

    }
}
