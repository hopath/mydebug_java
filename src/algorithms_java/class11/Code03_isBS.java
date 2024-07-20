package algorithms_java.class11;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_isBS {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    static class Info {
        int maxValue;
        int minValue;
        boolean isBS;

        public Info(int maxValue, int minValue, boolean isBS) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.isBS = isBS;
        }
    }


    public static boolean isBS(Node root) {
        return process(root).isBS;
    }

    private static Info process(Node root){
        if(root == null){
            return new Info(0, 0, true);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        int maxValue = left.minValue;
        int minValue = right.maxValue;
        boolean isBS = true;

        if(left.isBS == false){
            isBS = false;
        }
        if(right.isBS == false){
            isBS = false;
        }
        if(maxValue < root.value || minValue > root.value){
            isBS = false;
        }
        if(root.left != null){
            if(root.value < root.left.value){
                isBS = false;
            }else {
                minValue = root.left.value;
            }
        }else {
            minValue = root.value;
        }

        if(root.right != null){
            if(root.value > root.left.value){
                isBS = false;
            }else {
                maxValue = root.right.value;
            }
        }else {
            maxValue = root.value;
        }

        if(root.right == null && root.left == null){
            maxValue = root.value;
            minValue = root.value;
        }

        return new Info(maxValue, minValue, isBS);
    }
}
