package algorithms_java.class12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 张志伟
 * @version 1.0
 */
//会议时间安排最佳
public class Code02_BestArrange {

    static class Node {
        int start;
        int end;

        public Node(int left, int right) {
            this.start = left;
            this.end = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + start +
                    ", right=" + end +
                    '}';
        }
    }


    public static int getBestArrange01(Node[] nodes){
        return process(nodes, 0, 0);
    }


    public static int process(Node[] nodes, int done, int timeLine){
        if(nodes.length == 0){
            return done;
        }


        //若后续会议都安排不了，最大值就是已经安排的会议数
        int max = done;

        for(int i = 0; i < nodes.length; i++){
            if(nodes[i].start >= timeLine){
                Node[] next = removeIndexNode(nodes, i);
                max = Math.max(max, process(next, done + 1, nodes[i].end));
            }
        }
        return max;
    }


    public static Node[] removeIndexNode(Node[] time, int i) {
        ArrayList<Node> res = new ArrayList<>();
        for (int j = 0; j < time.length; j++) {
            if (j != i) {
                res.add(time[j]);
            }
        }
        return (Node[])res.toArray();
    }


    //贪心: 以结尾时间为标准，时间小的排前面
    public static class MyComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.end - o2.end;
        }
    }

    public static int getBestArrange02(Node[] nodes) {
        if(nodes == null && nodes.length == 0){
            return 0;
        }
        Arrays.sort(nodes, new MyComparator());
        int curLine = 0;
        int time = 0;

        for(int i = 0; i < nodes.length; i++){
            if(nodes[i].start >= curLine){
                time++;
                curLine = nodes[i].end;
            }
        }

        return time;
    }
}
