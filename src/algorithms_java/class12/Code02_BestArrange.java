package algorithms_java.class12;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 张志伟
 * @version 1.0
 */
/*
一些项目要占用一个会议室宣讲,会议室不能同时容纳两个项目的宣讲。
给你每一个项目开始的时间和结束的时间
你来安排宣讲的日程,要求会议室进行的宣讲的场次最多。
返回最多的宣讲场次。
 */
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


    /**
     *
     *返回能安排会议数量的最大值
     * @param nodes 还剩下多少回会议
     * @param done 之前已经安排多少会议了
     * @param timeLine 目前来到的时间点
     * @return
     */

    public static int process(Node[] nodes, int done, int timeLine){
        if(nodes.length == 0){
            return done;
        }

        int max = done;

        for(int i = 0; i < nodes.length; i++){
            if (nodes[i].start >= timeLine) {
                Node[] next = copyButExcept(nodes, i);
                max = Math.max(max, process(next, done + 1, nodes[i].end));
            }
        }

        return max;
    }


    public static Node[] copyButExcept(Node[] programs, int i) {
        Node[] ans = new Node[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
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

    // for test
    public static Node[] generatePrograms(int programSize, int timeMax) {
        Node[] ans = new Node[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Node(r1, r1 + 1);
            } else {
                ans[i] = new Node(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Node[] programs = generatePrograms(programSize, timeMax);
            if (getBestArrange01(programs) != getBestArrange02(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
