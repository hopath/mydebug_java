package algorithms_java.class10;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_InFollow {
    static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

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
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.parent = root;
        root.right.parent = root;
        root.left.left = new Node(4);
        root.left.left.parent = root.left;
        root.left.left.right = new Node(7);
        root.left.left.right.parent = root.left.left;
        root.right.left = new Node(5);
        root.right.left.parent = root.right;

        System.out.println(getInFollow(root, 7));
        System.out.println(getInFollow(root, 1));
        System.out.println(getInFollow(root, 5));
        System.out.println(getInFollow(root, 3));

    }

    public static Node getInFollow(Node root, int value){
        if(root == null){
            return null;
        }

        if(root.value == value){
            //若该节点没有右子树，则向上找找到节点是父节点的左孩子的位置，
            if(root.right == null){
//                while(root != null){
//                    Node last = root;
//                    root = root.parent;
//                    if(root == null){
//                        return null;
//                    }
//
//                    if(root.right != last){
//                        return root;
//                    }
//                }
//                return null;
                Node parent = root.parent;
                while (parent != null && parent.right == root){
                    root = parent;
                    parent = root.parent;
                }
                return parent;
            }

            //若该节点有右子树，那么其最左节点就是其后继节点
            root = root.right;
            while (root.left != null){
                root = root.left;
            }

            return root;
        }

        Node check = null;
        if((check = getInFollow(root.left, value)) != null){
            return check;
        }
        if((check = getInFollow(root.right, value)) != null){
            return check;
        }

        return null;
    }
}
