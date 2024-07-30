package algorithms_java.class11;


import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
//找包括a, b的公共节点
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


    static class Info1 {
        boolean findA;
        boolean findB;
        Node res;

        public Info1(boolean findA, boolean findB, Node res) {
            this.findA = findA;
            this.findB = findB;
            this.res = res;
        }
    }

    //当树中有相同元素时会出问题，例如a, b在同一个节点
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

        if (left.isCNode == true) {
            isCNode = true;
        }
        if (right.isCNode == true) {
            isCNode = true;
        }

        if (root.value == a.value) {
            isCNode = true;
        }
        if (root.value == b.value) {
            isCNode = true;
        }


        if (left.isCNode && right.isCNode || left.isCNode && (root.value == a.value ||
                root.value == b.value) || right.isCNode && (root.value == a.value || root
                .value == b.value)) {
            return new Info(false, root);
        }

        return new Info(isCNode, null);
    }

    public static Node getMinParentNode02(Node root, Node a, Node b) {
        return process01(root, a, b).res;
    }

    public static Info1 process01(Node root, Node a, Node b) {
        if (root == null) {
            return new Info1(false, false, null);
        }

        Info1 left = process01(root.left, a, b);
        if (left.res != null) {
            return left;
        }
        Info1 right = process01(root.right, a, b);
        if (right.res != null) {
            return right;
        }

        boolean findA = root == a || left.findA || right.findA;
        boolean findB = root == b || left.findB || right.findB;

        if (findA && findB) {
            return new Info1(true, true, root);
        }

        return new Info1(findA, findB, null);
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

    public static Node lowestAncestor2(Node head, Node a, Node b) {
        return process02(head, a, b).ans;
    }

    public static class Info3 {
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info3(boolean fA, boolean fB, Node an) {
            findA = fA;
            findB = fB;
            ans = an;
        }
    }

    public static Info3 process02(Node x, Node a, Node b) {
        if (x == null) {
            return new Info3(false, false, null);
        }
        Info3 leftInfo = process02(x.left, a, b);
        Info3 rightInfo = process02(x.right, a, b);
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info3(findA, findB, ans);
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
        in(root.left);
        System.out.print(root.value + " ");
        in(root.right);
    }

    @Test
    public void test() {
//        Node node = new Node(43);
//        node.left = new Node(85);
//        node.left.right = new Node(60);
//        node.right = new Node(43);
//        node.right.left = new Node(69);
//        node.right.right = new Node(88);
//        node.right.right.right = new Node(16);
//        node.right.right.right.right = new Node(36);
//        node.right.right.right.right.right = new Node(34);
        Node node = new Node(2);
        node.right = new Node(27);
        node.right.left = new Node(29);
        node.right.right = new Node(2);
        pre(node);
        System.out.println();
        in(node);
//        Node res1 = getMinParentNode02(node, new Node(2), new Node(29));
        Node res2 = lowestAncestor2(node, new Node(2), new Node(29));
//        System.out.println(res1 + " " + res2);
    }

    public static void main(String[] args) {
        int maxLevel = 3;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            Node minParentNode02 = lowestAncestor1(head, o1, o2);
            if ( minParentNode02 != getMinParentNode02(head, o1, o2)) {
                System.out.println(minParentNode02);
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
