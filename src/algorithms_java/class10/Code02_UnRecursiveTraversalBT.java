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

        pre01(root);
        pre02(root);
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

    //模拟递归路径
    public static void pre01(TNode tNode){
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

    /*
        先将头结点压栈, pop出一个节点打印pop出的节点的值
        之后将pop出的节点的右节点压栈，在将左节点压栈，在
        栈空之前如此循环
     */
    public static void pre02(TNode tNode){
        Stack<TNode> stack = new Stack<>();
        TNode cur = tNode;
        stack.push(cur);
        while(!stack.isEmpty()){
            TNode pop = stack.pop();
            System.out.print(pop.value + " ");
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }
        }
    }
    public static void in(TNode tNode){
        Stack<TNode> stack = new Stack<>();
        TNode cur = tNode;
        stack.push(cur);

        while(!stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            TNode pop = stack.pop();
            //如果栈已经空了，那么直接退出循环
            if(stack.isEmpty()){
                break;
            }
            System.out.print(pop.value + " ");
            cur = pop.right;
        }
    }

    public static void pos(TNode tNode){


    }

}
