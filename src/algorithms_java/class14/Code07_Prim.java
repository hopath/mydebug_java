package algorithms_java.class14;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code07_Prim {

    static class MyComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public static List<Edge> prim(Graph graph){
        List<Edge> res = new ArrayList<>();
        PriorityQueue<Edge> heap =
                new PriorityQueue<>(new MyComparator());

        Set<Integer> keySet = graph.nodes.keySet();
        Object[] keys = keySet.toArray();
        Node node = graph.nodes.get(keys[0]);
        for(Edge edge : node.edges) {
            heap.add(edge);
        }
        graph.nodes.remove(keys[0]);

        while(!graph.nodes.isEmpty()){
            Edge poll = heap.poll();
            Node next = graph.nodes.get(poll.to);
            res.add(poll);

            for(Edge edge : next.edges){
                heap.add(edge);
            }

            graph.nodes.remove(poll.from);
        }
        return res;
    }
}
