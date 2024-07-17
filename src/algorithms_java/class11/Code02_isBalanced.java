package algorithms_java.class11;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_isBalanced {
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

    @Test
    public void test(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        //root.left.left.left = new Node(6);

        System.out.println(isBalanced(root));
        System.out.println(isBalanced1(root));
    }

    //返回树的高度
    public static int getHeight(Node root){
        if(root == null){
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max(left, right) + 1;
    }

    public static boolean isBalanced(Node root){
        if(root == null){
            return true;
        }

        if(!isBalanced(root.left)){
            return false;
        }
        if(!isBalanced(root.right)){
            return false;
        }
        if(Math.abs(getHeight(root.left) - getHeight(root.right)) > 1){
            return false;
        }

        return true;
    }

    static class Info{
        boolean isBalance;
        int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
    public static boolean isBalanced1(Node root){
        return process(root).isBalance;
    }
    public static Info process(Node root){
        if(root == null){
            return new Info(true, 0);
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = true;
        if(leftInfo.isBalance == false){
            isBalance = false;
        }
        if(rightInfo.isBalance == false){
            isBalance = false;
        }
        if(Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalance = false;
        }

        return new Info(isBalance, height);
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
        int maxLevel = 52;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced(head) != isBalanced1(head)) {
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }
}
