package algorithms_java.class11;

import java.util.ArrayList;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code09_MaxHappy {
    static class Node {
        int value;
        ArrayList<Node> next;

        public Node(int value) {
            this.value = value;
        }
    }


    public static int getMaxHappy(Node root) {
        if(root.next == null){
            return root.value;
        }

        int childSum  = 0;

        for(int i = 0; i < root.next.size(); i++){
            int max  = getMaxHappy(root.next.get(i));
            childSum += max;
        }

        return childSum > root.value ? childSum : root.value;
    }
}
