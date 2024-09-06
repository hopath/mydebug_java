package algorithms_java.class14;

import algorithms_java.class08.Code02_HeapGreater;

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


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }


    public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        HashMap<Node, Integer> res = new HashMap<>();

        nodeHeap.addOrUpdateOrIgnore(from, 0);

        while (!nodeHeap.isEmpty()) {
            NodeRecord pop = nodeHeap.pop();
            Node node = pop.node;
            int distance = pop.distance;

            for (Edge edge : node.edges) {
                distance = distance + edge.weight;
                Node to = edge.to;
                nodeHeap.addOrUpdateOrIgnore(to, distance);
            }

            res.put(node, distance);
        }
        return res;
    }

}
