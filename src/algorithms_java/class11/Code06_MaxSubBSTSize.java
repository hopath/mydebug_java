package algorithms_java.class11;


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

        if(root.left != null){
            max = Math.max(root.value, left.maxValue);
            min = Math.min(root.value, left.minValue);
        }

        if(root.right != null){
            max = Math.max(max, right.maxSize);
            min = Math.min(min, right.minValue);
        }

        if(root.value > left.maxValue && root.value < right.minValue && isBST){
            return new Info(true, max, min, nodes, nodes);
        }else {
            return new Info(false, max, min, nodes, SubMaxsize);
        }


    }
}
