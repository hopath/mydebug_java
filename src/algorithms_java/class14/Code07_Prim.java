package algorithms_java.class14;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_Prim {

    static class MyComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static List<Edge> prim(Graph graph) {
        List<Edge> res = new ArrayList<>();
        PriorityQueue<Edge> heap =
                new PriorityQueue<>(new MyComparator());

        Set<Integer> keySet = graph.nodes.keySet();
        Object[] keys = keySet.toArray();
        Node node = graph.nodes.get(keys[0]);

        for (Edge edge : node.edges) {
            heap.add(edge);
        }
        graph.nodes.remove(keys[0]);

        while (!graph.nodes.isEmpty()) {
            Edge poll = heap.poll();
            Node next = graph.nodes.get(poll.to);
            res.add(poll);

            for (Edge edge : next.edges) {
                heap.add(edge);
            }

            graph.nodes.remove(poll.from);
        }
        return res;
    }

    public static List<Edge> prim02(Graph graph) {
        //小根堆，存放解锁的边
        PriorityQueue<Edge> heap =
                new PriorityQueue<>(new MyComparator());

        //存放解锁的节点
        HashSet<Node> set = new HashSet<>();

        //存放选取的边
        ArrayList<Edge> res = new ArrayList<>();

        //防止出现森林
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    heap.add(edge);
                }
                //结束后点都已经被选中了
                while (!heap.isEmpty()) {
                    Edge poll = heap.poll();
                    Node toNode = poll.to;
                    while (!set.contains(toNode)) {
                        set.add(toNode);
                        res.add(poll);
                        for (Edge edge : toNode.edges) {
                            heap.add(edge);
                        }
                    }
                }
            }
            //break;
        }
        return res;
    }

}
