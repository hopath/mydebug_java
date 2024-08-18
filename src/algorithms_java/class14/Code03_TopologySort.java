package algorithms_java.class14;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_TopologySort {

    public static void topologySort01(Graph graph) {
        Set<Integer> integers = graph.nodes.keySet();
        for (Integer i : integers) {
            for (Integer j : integers) {
                Node node = graph.nodes.get(j);
                if (node.in == 0) {
                    System.out.print(node.value + " ");
                    for (Node cur : node.nexts) {
                        cur.in--;
                    }
                }
            }
        }
    }



    public static List<Node> topologySort02(Graph graph) {
        //不破坏表结构，节点和入度的关系
        HashMap<Node, Integer> inMap = new HashMap<>();
        //入度为0的node加入队列
        Queue<Node> queue = new LinkedList<>();

        Set<Integer> integers = graph.nodes.keySet();
        for (Integer i : integers) {
            Node node = graph.nodes.get(i);
            inMap.put(node, i);
            if (node.in == 0) {
                queue.add(node);
            }
        }

        List<Node> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            res.add(poll);
            for (Node next : poll.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return res;
    }
}
