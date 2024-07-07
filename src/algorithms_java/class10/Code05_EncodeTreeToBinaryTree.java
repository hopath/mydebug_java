package algorithms_java.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_EncodeTreeToBinaryTree {
    static class Node{
        String value;
        Node[] nodes = new Node[25];

        public Node(String value) {
            this.value = value;
        }
    }

    static class Node1{
        String value;
        ArrayList<Node1> nodes = new ArrayList<>();

        public Node1(String value) {
            this.value = value;
        }
    }

    static class BNode{
        String value;
        BNode left;
        BNode right;

        public BNode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "BNode{" +
                    "value='" + value + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        Node root = new Node("a");
        root.nodes[0] = new Node("b");
        root.nodes[1] = new Node("c");
        root.nodes[2] = new Node("d");
        root.nodes[0].nodes[0] = new Node("e");
        root.nodes[0].nodes[1] = new Node("f");
        root.nodes[1].nodes[0] = new Node("g");
        root.nodes[1].nodes[1] = new Node("h");
        root.nodes[1].nodes[2] = new Node("i");
        root.nodes[1].nodes[3] = new Node("j");
        //printTree(root);
        pre(encodeTreeToBinaryTree(root));
    }

    //将多叉树转换成可以转回去的二叉树
    public static BNode encodeTreeToBinaryTree(Node root){
        Queue<Node> nodes = new LinkedList<>();
        //通过value建立多叉树与二叉树的联系
        HashMap<String, BNode> BNodes = new HashMap<>();
        nodes.add(root);

        while(!nodes.isEmpty()){
            Node poll = nodes.poll();

            //null节点时不操作
            if(poll != null){
                if(!BNodes.containsKey(poll.value)){
                    BNodes.put(poll.value, generateBNode(poll.value));
                }
                //若弹出节点没有孩子节点，则不进行操作
                if(poll.nodes[0] != null){
                    //以多叉树的层序遍历为驱动,通过HashMap连接以实现二叉树的建立
                    BNode bNode = BNodes.get(poll.value);
                    bNode.left = generateBNode(poll.nodes[0].value);
                    BNodes.put(bNode.left.value, bNode.left);
                    nodes.add(poll.nodes[0]);
                    int i = 1;
                    //完成右节点的连接
                    while(poll.nodes[i] != null){
                        nodes.add(poll.nodes[i]);
                        bNode.right = generateBNode(poll.nodes[i].value);
                        BNodes.put(bNode.right.value, bNode.right);
                        bNode = bNode.right;
                        i++;
                    }
                }

            }
        }
        return BNodes.get(root.value);
    }

    //递归实现将多叉树转换成可以转回去的二叉树
    public static void encodeTreeToBinaryTree2(Node1 root){
        if(root == null){
            return;
        }
        BNode bNode = generateBNode(root.value);
        bNode.left = en(root.nodes);
    }
    private static BNode en(ArrayList<Node1> nodes){

        for (Node1 node : nodes) {
            BNode head = null;
            BNode cur = null;

            if(head == null){
                head = generateBNode(node.value);
            }else{

            }
        }
    }

    public static void printTree(Node root){
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while(!nodes.isEmpty()){
            Node poll = nodes.poll();
            if(poll != null){
                System.out.print(poll.value + " ");
                int i = 0;
                while(poll.nodes[i] != null){
                    nodes.add(poll.nodes[i]);
                    i++;
                }
            }
        }
    }

    public static BNode generateBNode(String value){
        if(value == "#"){
            return null;
        }

        return new BNode(value);
    }

    public static void pre(BNode root){
        if(root == null){
            return;
        }
        System.out.print(root.value + " ");
        pre(root.left);
        pre(root.right);
    }
}

