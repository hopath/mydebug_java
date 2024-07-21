package algorithms_java.class11;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_isBST {
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


    public static boolean isBST1(Node root) {
        return process(root).isBS;
    }

    private static Info process(Node root){
        if(root == null){
            return new Info(0, 0, true);
        }

        Info left = process(root.left);
        Info right = process(root.right);

        //若左右树为空则最大最小值为root值
        int maxValue = root.value;
        int minValue = root.value;
        boolean isBS = true;

        if(left.isBS == false){
            isBS = false;
        }
        if(right.isBS == false){
            isBS = false;
        }
        if(root.left != null){
            if(root.value < left.maxValue){
                isBS = false;
            }else {
                maxValue = Math.max(root.value, left.maxValue);
            }
        }

        if(root.right != null){
            //既要判断左树小于root同时要保证root大于左树的最小值
            if(root.value > right.minValue){
                isBS = false;
            }else {
                minValue = Math.max(root.value, right.minValue);
            }
        }

        return new Info(maxValue, minValue, isBS);
    }

    public static class Node1 {
        public int value;
        public Node1 left;
        public Node1 right;

        public Node1(int data) {
            this.value = data;
        }
    }

    public static boolean isBST2(Node root){
        if(root == null){
            return true;
        }
        return process1(root).isBS;
    }

    private static Info process1(Node root){
        if(root == null){
            return null;
        }
        Info left = process1(root.left);
        Info right = process(root.right);

        boolean isBS = true;
        int max = root.value;
        int min = root.value;

        if(left != null && !left.isBS){
            isBS = false;
        }
        if(right != null && !right.isBS){
            isBS = false;
        }

        if(left != null){
            max = Math.max(root.value, left.maxValue);
            min = Math.min(root.value, left.minValue);
        }
        if(right != null){
            min = Math.min(root.value, right.minValue);
            max = Math.max(root.value, right.maxValue);
        }

        if(left != null && root.value <= left.maxValue){
            isBS = false;
        }
        if(right != null && root.value >= right.minValue){
            isBS = false;
        }

        return new Info(max, min, isBS);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }

}
