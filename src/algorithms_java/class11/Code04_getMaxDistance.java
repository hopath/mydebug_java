package algorithms_java.class11;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_getMaxDistance {
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
        int high;
        int maxDistance;

        public Info(int high, int maxDistance) {
            this.high = high;
            this.maxDistance = maxDistance;
        }
    }

    public static int getMaxDistance(Node root){
        return process(root).maxDistance;
    }
    public static Info process(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        int high = left.high > right.high ? left.high + 1 : right.high + 1;

        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        int p3 = left.high + right.high + 1;

        int maxDistance = Math.max(Math.max(p1, p2), p3);

        return new Info(high, maxDistance);


    }
}
