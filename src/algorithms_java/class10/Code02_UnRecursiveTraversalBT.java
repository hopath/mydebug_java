package algorithms_java.class10;

import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_UnRecursiveTraversalBT {

    public static void main(String[] args) {
        TNode root = new TNode(1);
        root.left = new TNode(2);
        root.right = new TNode(3);
        root.left.left = new TNode(4);
        root.left.left.left = new TNode(10);
        root.right.right = new TNode(7);
        root.right.right.left = new TNode(8);
        root.left.left.right = new TNode(5);
        root.left.left.right.left = new TNode(6);

        pos(root);
    }
    static class TNode{
        TNode left;
        TNode right;
        int value;

        public TNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TNode{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void pre(TNode tNode){
        Stack<TNode> stack = new Stack<TNode>();
        TNode cur = tNode;
        stack.push(cur);

        while(!stack.empty()){
            while(cur != null){
                System.out.print(cur.value + " ");
                stack.push(cur);
                cur = cur.left;
            }
            TNode pop = stack.pop();
            cur = pop.right;
        }
    }

    public static void pos(TNode tNode){
        Stack<TNode> stack = new Stack<>();
        TNode cur = tNode;
        stack.push(cur);

        while(!stack.isEmpty()){
            while(cur != null){
                if(cur != tNode){
                    stack.push(cur);
                }
                cur = cur.left;
            }
            TNode pop = stack.pop();
            System.out.print(pop.value + " ");
            cur = pop.right;
        }
    }

}
