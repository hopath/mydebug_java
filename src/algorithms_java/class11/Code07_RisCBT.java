package algorithms_java.class11;

import java.util.LinkedList;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_RisCBT {

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
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    //用递归判读是否为二叉树
    public static boolean isCBT(Node root) {
        return process(root).isCBT;
    }

    public static Info process(Node root){
        if(root == null){
            return new Info(true, true, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = false;
        boolean isCBT = false;

        //左右树都是满的，且高度一致
        if(left.isFull && right.isFull && left.height == right.height){
            isCBT = true;
            isFull = true;
        }

        //右数是满的，左树是完全二叉树，且左树高度高于右数1个高度
        if(right.isFull && left.isCBT && left.height - 1 == right.height){
            isCBT = true;
        }

        //左树是满的，右数是满的，且左树高度高于右数1个高度
        if(left.isFull && right.isFull && left.height - 1 == right.height){
            isCBT = true;
        }

        //左树是满的，右数是完全二叉树，且高度相同
        if(left.isFull && right.isCBT && left.height == right.height){
            isCBT = true;
        }

        return new Info(isFull, isCBT, height);
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT(head)) {
                pre(head);
                System.out.println();
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }

    public static void pre(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.value + " ");
        pre(root.left);
        pre(root.right);
    }

}
