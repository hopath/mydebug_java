package algorithms_java.class14;

/**
 * @author 张志伟
 * @version 1.0
 */
public class GraphGenerator {

    public Graph generator(int matrix[][]) {
        Graph graph = new Graph();
        for (int[] cur : matrix) {
            int weight = cur[0];
            int from = cur[1];
            int to = cur[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from,  new Node(from));
            }

            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            graph.edges.add(edge);

            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
        }

        return graph;
    }
}
