package algorithms_java.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_isCBT {
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

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        //root.left.right = new Node(5);

        System.out.println(isCBT(root));
        System.out.println(isCBT1(root));

    }

    public static boolean isCBT(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            //左节点为空，右节点不为空则直接退出，不是完全二叉树
            if (poll.left == null && poll.right != null) {
                return false;
            }

            if (poll.left != null) {
                nodes.add(poll.left);
            }
            if (poll.right != null) {
                nodes.add(poll.right);
            }

            //此时只要节点不全，后面必须都是叶子节点
            if (!(poll.left != null && poll.right != null)) {
                while (!nodes.isEmpty()) {
                    poll = nodes.poll();
                    if (poll.left != null || poll.right != null) {
                        return false;
                    }

                    if (poll.left != null) {
                        nodes.add(poll.left);
                    }
                    if (poll.right != null) {
                        nodes.add(poll.right);
                    }
                }
            }
        }
        return true;
    }

    public static boolean isCBT1(Node root) {
        if (root == null) {
            return true;
        }

        boolean leaf = false;
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();

            if (leaf && (poll.left != null || poll.right != null) ||
                    poll.left == null && poll.right != null) {

                return false;
            }

            if (poll.left != null) {
                nodes.add(poll.left);
            }
            if (poll.right != null) {
                nodes.add(poll.right);
            }

            if (poll.left == null || poll.right == null) {
                leaf = true;
            }
        }

        return true;
    }
}
