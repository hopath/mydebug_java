package algorithms_java.class14;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code06_Kruskal {

    static class Union_Find {
        HashMap<Node, Node> parentMap;
        HashMap<Node, Integer> sizeMap;

        public Union_Find(Collection<Node> nodes) {
            this.parentMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
            for (Node node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node find(Node a) {
            Stack<Node> stack = new Stack<>();

            while (parentMap.get(a) != a) {
                stack.push(a);
                a = parentMap.get(a);
            }

            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), a);
            }

            return a;
        }

        public boolean isSameCollection(Node n1, Node n2) {
            Node f1 = find(n1);
            Node f2 = find(n2);
            if (f1 != f2) {
                Integer s1 = sizeMap.get(f1);
                Integer s2 = sizeMap.get(f2);

                Node large = s1 > s2 ? f1 : f2;
                Node small = large == f1 ? f2 : f1;

                parentMap.put(small, large);
                sizeMap.put(large, s1 + s2);
                sizeMap.remove(small);

                return false;
            }
            return true;
        }
    }

    static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static List<Edge> kruskal(Graph graph) {
        Union_Find unionFind = new Union_Find(graph.nodes.values());
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeComparator());
        List<Edge> res = new ArrayList<>();


        for (Edge edge : graph.edges) {
            heap.add(edge);
        }

        while (!heap.isEmpty()) {
            Edge poll = heap.poll();
            if (!unionFind.isSameCollection(poll.from, poll.to)) {
                res.add(poll);
            }
        }

        return res;
    }
}
