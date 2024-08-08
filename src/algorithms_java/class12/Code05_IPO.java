package algorithms_java.class12;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_IPO {

    static class Node {
        int c;
        int p;

        public Node(int c, int p) {
            this.c = c;
            this.p = p;
        }


    }

    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }


    public static int findMaximizedCapital(int K, int W, int[] costs, int[] profits) {
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        for(int i = 0; i < costs.length; i++){
            minCostQ.add(new Node(costs[i], profits[i]));
        }

        for(int i = 0; i < K; i++){
            while(!maxProfitQ.isEmpty() && minCostQ.peek().c <= W){
                maxProfitQ.add(minCostQ.poll());
            }

            if(maxProfitQ.isEmpty()){
                return W;
            }

            W += maxProfitQ.poll().p;
        }

        return W;
    }
}
