package algorithms_java.class14;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.push(node);
        set.add(node);
        System.out.print(node.value + " ");

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node cur : pop.nexts) {
                if (!set.contains(cur)) {
                    stack.push(pop);
                    stack.add(cur);
                    set.add(cur);
                    System.out.print(cur.value + " ");
                    break;
                }
            }
        }
    }
}
