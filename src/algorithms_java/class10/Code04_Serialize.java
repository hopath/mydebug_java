package algorithms_java.class10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_Serialize {
    static class TNode{
        int value;
        TNode left;
        TNode right;

        public TNode(int value) {
            this.value = value;
        }
    }

    //前序遍历序列化
    public static Queue<String> preSerial(TNode tNode){
        Queue<String> ans = new LinkedList();
        pre(tNode, ans);

        return ans;
    }

    public static void pre(TNode tNode, Queue<String> ans){
        if(tNode == null){
           ans.add("#");
           return;
        }

        ans.add(String.valueOf(tNode.value));
        pre(tNode.left, ans);
        pre(tNode.right, ans);
    }

    public static TNode buildByPreQueue(Queue<String> preList){
        if(preList == null || preList.size() == 0){
            return null;
        }
        return preb(preList);
    }

    //前序遍历反序列化
    public static TNode preb(Queue<String> preList){
        String value = preList.poll();
        if(value == "#"){
            return null;
        }

        TNode head = new TNode(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);

        return head;
    }

    //层序遍历反序列化
    public static Queue<String> levelSerial(TNode tNode){
        Queue<String> ans = new LinkedList();
        level(tNode, ans);
        return ans;
    }

    public static void level(TNode tNode, Queue<String> ans){
        Queue<TNode> queue = new LinkedList();
        TNode cur = tNode;
        queue.add(cur);

        while(!queue.isEmpty()){
            TNode poll = queue.poll();

            if(poll == null){
                ans.add("#");
            }else {
                ans.add(String.valueOf(poll.value));
                if(cur.left != null){
                    ans.add(String.valueOf(cur.left.value));
                    queue.add(cur.left);
                }else{
                    ans.add("#");
                }

                if(cur.right != null){
                    ans.add(String.valueOf(cur.right.value));
                    queue.add(cur.left);
                }else{
                    ans.add("#");
                }
            }
        }
    }


    public static TNode generateTNode(String value){
        if(value == "#"){
            return null;
        }

        return new TNode(Integer.valueOf(value));
    }

    public static TNode buildByLevel(Queue<String> levelList){
        if(levelList == null || levelList.size() == 0){
            return null;
        }

        Queue<TNode> queue = new LinkedList();
        TNode tNode = generateTNode(levelList.poll());

        if(tNode == null){
            return null;
        }else {
            queue.add(tNode);
        }

        TNode ans = null;
        while(!queue.isEmpty()){
            ans = queue.poll();

//            String left = levelList.poll();
//            if(left == "#"){
//                ans.left = null;
//            }else{
//                ans.left = new TNode(Integer.valueOf(left));
//                queue.add(ans.left);
//            }
//
//            String right = levelList.poll();
//            if(right == "#"){
//                ans.right = null;
//            }else{
//                ans.right = new TNode(Integer.valueOf(right));
//                queue.add(ans.right);
//            }


            ans.left = generateTNode(levelList.poll());
            ans.right = generateTNode(levelList.poll());
            if(ans.left != null){
                queue.add(ans.left);
            }

            if(ans.right != null){
                queue.add(ans.right);
            }
        }


        return ans;
    }

}
