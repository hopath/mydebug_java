package algorithms_java.class14;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_BFS {

    @Test
    public void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.nexts.add(node2);
        node1.nexts.add(node3);
        node2.nexts.add(node3);
        node2.nexts.add(node4);
        node4.nexts.add(node3);

        bfs(node1);
    }

    public void bfs(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();

        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value);
            for (Node cur : poll.nexts) {
                if (!set.contains(cur)) {
                    queue.add(cur);
                    set.add(cur);
                }
            }
        }
    }
}
