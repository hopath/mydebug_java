package algorithms_java.class11;


import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code06_MaxSubBSTSize {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Info {
        boolean isBST;
        //子树最大值
        int maxValue;
        int minValue;
        //是二叉树时的节点数
        int nodes;
        int maxSize;

        public Info(boolean isBST, int maxValue, int minValue, int nodes, int maxSize) {
            this.isBST = isBST;
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.nodes = nodes;
            this.maxSize = maxSize;
        }

        public Info(int maxValue, int minValue, int nodes, int maxSize) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.nodes = nodes;
            this.maxSize = maxSize;
        }
    }

    public static int maxSubBSTSize(Node root) {
        return process(root).maxSize;
    }

    public static Info process(Node root) {
        if(root == null){
            return new Info(true, 0, 0, 0, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        boolean isBST = true;
        int max = root.value;
        int min = root.value;
        int nodes = left.nodes + right.nodes + 1;
        int SubMaxsize = Math.max(left.maxSize, right.maxSize);

        if(!left.isBST){
            isBST = false;
        }

        if(!right.isBST){
            isBST = false;
        }

        //更新最大最小值
        if(root.left != null){
            max = Math.max(root.value, left.maxValue);
            min = Math.min(root.value, left.minValue);
        }

        if(root.right != null){
            max = Math.max(max, right.maxSize);
            min = Math.min(min, right.minValue);
        }

        /*
            若为二叉搜索树，则返回整棵树的节点数
            否则返回左右树最大节点数
         */
        if(root.value > left.maxValue && root.value < right.minValue && isBST){
            return new Info(true, max, min, nodes, nodes);
        }else {
            return new Info(false, max, min, nodes, SubMaxsize);
        }

    }


    public static int maxSubBSTSize1(Node root) {

        return process1(root).maxSize;
    }

    public static Info process1(Node root) {
        if(root == null){
            return new Info(0, 0, 0, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        boolean isBST = true;
        int max = root.value;
        int min = root.value;
        int nodes = left.nodes + right.nodes + 1;
        int SubMaxsize = Math.max(left.maxSize, right.maxSize);

        //更新最大最小值
        if(root.left != null){
            max = Math.max(root.value, left.maxValue);
            min = Math.min(root.value, left.minValue);
        }

        if(root.right != null){
            max = Math.max(max, right.maxSize);
            min = Math.min(min, right.minValue);
        }

        /*
            若为二叉搜索树，则返回整棵树的节点数
            否则返回左右树最大节点数
         */
        if(root.value > left.maxValue && root.value < right.minValue
                && left.maxSize == left.nodes && right.maxSize == right.nodes){
            return new Info(true, max, min, nodes, nodes);
        }else {
            return new Info(false, max, min, nodes, SubMaxsize);
        }

    }


    @Test
    public void test(){
        Node node = new Node(17);
        node.right = new Node(20);
        System.out.println(maxSubBSTSize1(node));
    }








    public static class Info1 {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info1(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }

    public static int maxSubBSTSize3(Node root) {
        if(root == null){
            return 0;
        }
        return process3(root).maxBSTSubtreeSize;
    }

    public static Info1 process3(Node x) {
        if (x == null) {
            return null;
        }
        Info1 leftInfo = process3(x.left);
        Info1 rightInfo = process3(x.right);
        int max = x.value;
        int min = x.value;
        int allSize = 1;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }

        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info1(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
    }







    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            int m1 = maxSubBSTSize3(head);
            int m2 = maxSubBSTSize(head);
            if (m1 != m2) {
                pre(head);
                System.out.println();
                System.out.println(m1 + " " + m2);
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
}
