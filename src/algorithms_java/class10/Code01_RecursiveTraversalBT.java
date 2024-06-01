package algorithms_java.class10;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_RecursiveTraversalBT {

    static class TNode{
        TNode left;
        TNode right;
        int value;

        public TNode(int value) {
            this.value = value;
        }
    }

    public static void f(TNode tNode){
        if(tNode == null){
            return;
        }
        //1
        f(tNode.left);
        //2
        f(tNode.right);
        //3
    }

    public static void pre(TNode tNode){
        if(tNode == null){
            return;
        }
        System.out.println(tNode.value);
        pre(tNode.left);
        pre(tNode.right);
    }

    public static void in(TNode tNode){
        if(tNode == null){
            return;
        }
        in(tNode.left);
        System.out.println(tNode.value);
    }

    public static void pos(TNode tNode){
        if(tNode == null){
            return;
        }
        pos(tNode.left);
        pos(tNode.right);
        System.out.println(tNode.value);
    }
}
