package algorithms_java.class10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code06_TreeMaxWidth {

    static class Node{
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

    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.left = new Node(8);

        System.out.println(getMaxWidth(root));
        System.out.println(getMaxWidth02(root));

    }
    public static int getMaxWidth(Node root){
        if(root == null){
            return 0;
        }
        ArrayList<Integer> levels = new ArrayList<>();
        int cnt = 0;
        //判断二叉层数
        int i = -1;
        int n = 0;

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while(!nodes.isEmpty()){
            Node poll = nodes.poll();
            i++;
            if(poll != null){
                nodes.add(poll.left);
                if(poll.left != null){
                    cnt++;
                }
                nodes.add(poll.right);
                if(poll.right != null){
                    cnt++;
                }
            }

            if(i == getCapitalTwo(n) || i == 0){
                levels.add(cnt);
                cnt = 0;
                n++;
            }
        }
        int max = 0;
        for(int u = 1; u < levels.size(); u++){
            if(levels.get(u) > levels.get(max)){
                max = u;
            }
        }

        return levels.get(max) > 1 ? levels.get(max) : 1;
    }

    public static int getMaxWidth02(Node root){
        ArrayList<Integer> levelNums = new ArrayList<>();
        Queue<Node> nodes = new LinkedList<>();
        Node curEnd = null;
        Node nextEnd =null;
        int cnt = 1;
        nodes.add(root);
        curEnd = root;
        nextEnd = root;

        while(!nodes.isEmpty()){
            Node poll = nodes.poll();

            if(poll.left != null){
                nodes.add(poll.left);
                nextEnd = poll.left;
            }
            if(poll.right != null){
                nodes.add(poll.right);
                nextEnd = poll.right;
            }

            if(poll != curEnd){
                cnt++;
            }else {
                curEnd = nextEnd;
                levelNums.add(cnt);
                cnt = 1;
            }
        }
        int max = 0;
        for(int u = 1; u < levelNums.size(); u++){
            if(levelNums.get(u) > levelNums.get(max)){
                max = u;
            }
        }

        return levelNums.get(max);
    }

    public static void pre(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.value + " ");
        pre(root.left);
        pre(root.right);
    }

    public static int getCapitalTwo(int n){
        int res = 1;
        for(int i = 0; i < n; i++){
            res *= 2;
        }

        return res;
    }
}
