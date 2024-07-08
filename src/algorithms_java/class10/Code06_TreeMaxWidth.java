package algorithms_java.class10;

import java.util.ArrayList;
import java.util.LinkedList;

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
    }
    public static int getMaxWidth(Node root){
        ArrayList<Integer> levels = new ArrayList<>();
        int cnt = 0;
        int i = -1;

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while(!nodes.isEmpty()){
            Node poll = nodes.poll();
            i++;


            if(poll != null){
                nodes.add(root.left);
                if(root.left != null){
                    cnt++;
                }
                nodes.add(root.right);
                if(root.right != null){
                    cnt++;
                }
            }

            if(i % 2 == 0 && i == 0){
                levels.add(cnt);
                cnt = 0;
            }
        }
        int max = 0;
        for(int u = 1; u < levels.size(); u++){
            if(levels.get(u) > levels.get(max)){
                max = u;
            }
        }

        return levels.get(max);
    }
}
