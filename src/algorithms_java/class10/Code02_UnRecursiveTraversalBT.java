package algorithms_java.class10;

import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_UnRecursiveTraversalBT {

    public static void main(String[] args) {
        TNode root = new TNode(3);
        root.left = new TNode(2);
        root.right = new TNode(1);
        root.left.left = new TNode(4);
        root.left.right = new TNode(5);
        root.right.left = new TNode(8);
        root.right.right = new TNode(9);
        root.left.left.left = new TNode(10);

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

        while(cur != null){
            System.out.print(cur.value + " ");
            stack.push(cur);
            cur = cur.left;
        }


        while(!stack.empty()){
            TNode pop = stack.pop();
            if(pop.right != null){
                System.out.print(pop.right.value + " ");
            }
        }

        cur = tNode.right;
        while(cur != null){
            if(cur != tNode.right){
                System.out.print(cur.value + " ");
            }
            stack.push(cur);
            cur = cur.left;
        }

        while(!stack.empty()){
            TNode pop = stack.pop();
            if(pop.right != null){
                System.out.print(pop.right.value + " ");
            }
        }
    }

    public static void pos(TNode tNode){
        Stack<TNode> stack = new Stack<>();

        TNode cur = tNode;
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }

        TNode pop = null;
        while(pop != tNode){
            pop = stack.pop();
            if(pop.left != null){
                System.out.print(pop.left.value + " ");
            }
            if(pop.right != null){
                System.out.print(pop.right.value + " ");
            }
        }

        stack.push(pop);
        cur = tNode.right;
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }

        pop = stack.pop();
        while(pop != tNode){
            if(pop.left != null){
                System.out.print(pop.left.value + " ");
            }
            if(pop.right != null){
                System.out.print(pop.right.value + " ");
            }
            pop = stack.pop();
        }

        System.out.print(tNode.value);
    }

}
