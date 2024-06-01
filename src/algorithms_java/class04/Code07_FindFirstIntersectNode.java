package algorithms_java.class04;

import java.util.HashSet;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_FindFirstIntersectNode {
    static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(3);
        head1.next = new Node(4);
        head1.next.next = new Node(5);

        Node head2 = new Node(2);
        head2.next = new Node(6);
        head2.next = head1.next;

        System.out.println(findFirstIntersectNode(head1, head2));
    }


    public static Node findFirstIntersectNode(Node head1, Node head2){
        Node loop01 = isLoop03(head1);
        Node loop02 = isLoop03(head2);

        if(loop01 == null && loop02 == null){
            return noLoop02(head1, head2);
        }

        if(loop01 != null && loop02 != null){
            return twoLoop(head1, head2);
        }

        return null;
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
    /*
        若是循环链表，则不用考虑边界影响
        若不是循环链表，要从  0,1,2,3四种情况进行考虑，
        来卡边界
     */
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
        cur2 =  cur1 == head1 ? head2 : head1;

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

    public static Node twoLoop(Node head1, Node head2){
        //返回入环节点
        Node loop1 = isLoop03(head1);
        Node loop2 = isLoop03(head2);
        /*
            若入环节点相同则是在入环前相交
         */
        if(loop1 == loop2){
            int n = 0;
            Node cur1 = head1;   Node cur2 = head2;

            //算出两个链表到入环节点距离的差值
            while(cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }

            //重新定向长链表和短链表
            cur1 =  n > 0 ? head1 : head2;
            cur2 =  cur1 == head1 ? head2 : head1;

            n = Math.abs(n);

            //长链表先走多的距离
            for(int i = 0; i < n; i++){
                cur1 = cur1.next;
            }

            /*
                此时两个链表距离入环节点相同，同时距离相交节点也相同
                故而指针相遇处就是相交节点
             */
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }

        //若入环节点不同则可能不想交或者相交在入环节点之后

        //注意此时head1入环节点处一定不相交
        Node cur = loop1.next;

        /*
            若cur走完一圈还没有遇到head2的入环节点则证明两链表不相交
            否则证明相交，返回head1或head2的入环节点
         */
        while(cur != loop1){
            if(cur == loop2) {
                return loop2;
            }
            cur = cur.next;
        }

        return null;
    }
}
