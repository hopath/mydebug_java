package algorithms_java.class13;


import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
//并查集
public class Code01_UnionFind {

    static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    static class UnionFind<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parentMap;
        private HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> list) {
            nodes = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V cur : list) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFatherNode(V a) {
            Stack<Node<V>> stack = new Stack<>();
            Node<V> node = nodes.get(a);

            while (parentMap.get(node) != node) {
                stack.push(node);
                node = parentMap.get(node);
            }

            while (!stack.isEmpty()) {
                Node<V> pop = stack.pop();
                parentMap.put(pop, node);
            }

            return node;
        }


        public boolean isSameSet(V a, V b) {
            Node<V> node1 = findFatherNode(a);
            Node<V> node2 = findFatherNode(b);

            return node1 == node2;
        }


        public void union(V a, V b) {
            Node<V> f1 = findFatherNode(a);
            Node<V> f2 = findFatherNode(b);

            if (f1 != f2) {
                Integer s1 = sizeMap.get(f1);
                Integer s2 = sizeMap.get(f2);

                Node<V> large = s1 > s2 ? f1 : f2;
                Node<V> small = large == f1 ? f2 : f1;

                parentMap.put(large, small);
                sizeMap.put(large, s1 + s2);
                sizeMap.remove(small);
            }
        }

        public int size() {
            return sizeMap.size();
        }
    }

    static class UnionFind01 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sites;

        public UnionFind01(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sites = N;

            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int p = 0;
            while (parent[i] != i) {
                help[p++] = i;
                i = parent[i];
            }

            for(p--; p >= 0; p--){
                parent[help[p]] = i;
            }

            return i;
        }

        public void union(int i, int j) {
            int p1 = find(i);
            int p2 = find(j);

            if(p1 != p2){
                int large = size[p1] > size[p2] ? p1 : p2;
                int small = large == p1 ? p2 : p1;
                parent[small] = large;
                size[large] = size[p1] + size[p2];
                sites--;
            }
        }

        public int sites(){
            return sites;
        }
    }
}
