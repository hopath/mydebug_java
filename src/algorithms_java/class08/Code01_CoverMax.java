package algorithms_java.class08;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_CoverMax {
    private static class Line{
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    private static class MyComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }
    public static void maxCover01(int[][] lines){

    }
    public static int maxCover02(int[][] lines){
        ArrayList<Line> lineArrayList = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;

        for(int i = 0; i < lines.length; i++){
            Line line = new Line(lines[i][0], lines[i][1]);
            lineArrayList.add(line);
        }
        lineArrayList.sort(new MyComparator());

        for(int i = 0; i < lineArrayList.size(); i++){
            if(!heap.isEmpty() && heap.peek() <= lineArrayList.get(i).start){
                heap.poll();
            }
            heap.add(lineArrayList.get(i).end);
            max = heap.size() > max ? heap.size() : max;
        }
        return max;

    }

    @Test
    public void test(){
        int arr[][] = {{1, 2}, {4, 6}, {2, 3}, {0, 3}, {8, 4}};
        int res = maxCover02(arr);
        System.out.println(res);
    }
}
