package algorithms_java.class11;


import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code08_MinParent {
    static class Node {
        int value;
        Node left;
        Node right;

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

    static class Info {
        boolean isCNode;
        Node res;

        public Info(boolean isCNode, Node res) {
            this.isCNode = isCNode;
            this.res = res;
        }
    }

    public static Node getMinNode(Node root, Node a, Node b) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        return process(root, a, b).res;
    }

    public static Info process(Node root, Node a, Node b) {
        if (root == null) {
            return new Info(false, null);
        }

        Info left = process(root.left, a, b);
        if (left.res != null) {
            return left;
        }

        Info right = process(root.right, a, b);
        if (right.res != null) {
            return right;
        }


        boolean isCNode = false;


        if (left.isCNode && right.isCNode || left.isCNode && (root.value == a.value ||
                root.value == b.value) || right.isCNode && (root.value == a.value || root
                .value == b.value)) {
            return new Info(false, root);
        }

        if(left.isCNode == true){
            isCNode = true;
        }
        if(right.isCNode == true){
            isCNode = true;
        }

        if (root.value == a.value) {
            isCNode = true;
        }
        if (root.value == b.value) {
            isCNode = true;
        }

        return new Info(isCNode, null);
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }


    public static void pre(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        pre(root.left);
        pre(root.right);
    }

    public static void in(Node root) {
        if (root == null) {
            return;
        }
        pre(root.left);
        System.out.print(root.value + " ");
        pre(root.right);
    }

    @Test
    public void test() {
        Node node = new Node(23);
        node.left = new Node(66);
        node.left.right = new Node(69);
        node.left.right.right = new Node(0);

        Node res = getMinNode(node, new Node(0), new Node(66));
        System.out.println(res);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != getMinNode(head, o1, o2)) {
                pre(head);
                System.out.println();
                in(head);
                System.out.println();
                System.out.println(o1.value + " " + o2.value);
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }
}
