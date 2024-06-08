package algorithms_java.class10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_LevelTraversalBT {
    static class TNode{
        int value;
        TNode left;
        TNode right;

        public TNode(int value) {
            this.value = value;
        }
    }

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

       level(root);
    }

    //用队列实现层序遍历
    public static void level(TNode tNode){
        if(tNode == null){
            return;
        }
        Queue<TNode> queue = new LinkedList<>();
        TNode cur = tNode;
        queue.add(cur);

        while (!queue.isEmpty()){
            TNode poll = queue.poll();

            System.out.print(poll.value + " ");
            if(poll.left != null){
                queue.add(poll.left);
            }

            if(poll.right != null){
                queue.add(poll.right);
            }
        }
    }
}

