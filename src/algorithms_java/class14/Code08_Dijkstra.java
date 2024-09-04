package algorithms_java.class14;

import java.util.*;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code08_Dijkstra {

    public static Node getMinNodeExceptSet(HashMap<Node, Integer> hashMap, HashSet<Node> set) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;

        Set<Node> nodes = hashMap.keySet();
        for (Node node : nodes) {
            Integer distance = hashMap.get(node);
            if (distance < minDistance && !set.contains(node)) {
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }

    public static HashMap<Node, Integer> dijkstra(Node from) {
        HashMap<Node, Integer> hashMap = new HashMap<>();

        HashSet<Node> set = new HashSet<>();

        //获得除set中的Node中最小值
        Node minNode = getMinNodeExceptSet(hashMap, set);
        hashMap.put(from, 0);

        while (minNode != null) {
            Integer distance = hashMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (!hashMap.containsKey(to)) {
                    hashMap.put(to, distance + edge.weight);
                } else {
                    hashMap.put(to, Math.min(hashMap.get(to), distance + edge.weight));
                }
            }
            set.add(minNode);
            minNode = getMinNodeExceptSet(hashMap, set);
        }

        return hashMap;
    }

}
