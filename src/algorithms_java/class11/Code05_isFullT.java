package algorithms_java.class11;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_isFullT {
    //是否是满二叉树

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Info {
        boolean isFull;
        int height;
        int num;

        public Info(boolean isFull, int height, int num) {
            this.isFull = isFull;
            this.height = height;
            this.num = num;
        }

        public Info(int height, int num) {
            this.height = height;
            this.num = num;
        }
    }

    public static boolean isFullT1(Node root) {
        return process1(root).isFull;
    }

    private static Info process1(Node root) {
        if (root == null) {
            return new Info(true, 0, 0);
        }

        Info left = process1(root.left);
        Info right = process1(root.right);

        boolean isFull = true;
        int height = Math.max(left.height, right.height) + 1;
        int num = left.num + right.num + 1;

        if (!left.isFull) {
            isFull = false;
        }

        if (!right.isFull) {
            isFull = false;
        }

        if ((1 << height) - 1 != num) {
            isFull = false;
        }

        return new Info(isFull, height, num);

    }

    public static boolean isFullT2(Node root) {
        int height = process(root).height;
        int num = process(root).num;

        return (1 << height) - 1 == num;
    }

    private static Info process(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        int height = Math.max(left.height, right.height) + 1;
        int num = left.num + right.num + 1;

        return new Info(height, num);
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    //生成完全搜索二叉树
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFullT1(head) != isFullT2(head)) {
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
