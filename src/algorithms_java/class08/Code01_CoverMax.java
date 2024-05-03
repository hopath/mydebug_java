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
    public static int maxCover01(int[][] lines){
        int minValue = Integer.MAX_VALUE;
        int maxValue = 0;
        int maxNum = 0;

        for(int i = 0; i < lines.length; i++){
            if(lines[i][0] < minValue){
                minValue = lines[i][0];
            }
            if(lines[i][1] > maxValue){
                maxValue = lines[i][1];
            }
        }
        for(float i = (float)(minValue + 0.5); i < maxValue; i++){
            int cnt = 0;
            for(int j = 0; j < lines.length; j++){
                if(i > lines[j][0] && i < lines[j][1]){
                    cnt++;
                }
            }
            if(cnt > maxNum){
                maxNum = cnt;
            }
        }
        return maxNum;
    }
    //lines是一个二维数组列数为2，零位置为线段起始，1位置为线段末尾
    public static int maxCover02(int[][] lines){
        //将线段起始位置包装成一个类，方便排序
        ArrayList<Line> lineArrayList = new ArrayList<>();
        //小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;

        for(int i = 0; i < lines.length; i++){
            Line line = new Line(lines[i][0], lines[i][1]);
            lineArrayList.add(line);
        }
        //对线段进行排序
        lineArrayList.sort(new MyComparator());

        for(int i = 0; i < lineArrayList.size(); i++){
            //若小根堆内的元素小于线段起始位置则弹出
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
        int times = 10000;
        int N = 1000;
        int L = 100;
        int R = 1000;
        for(int i = 0; i < times; i++){
            int arr[][] = Code01_CoverMax.generateLines(N, L, R);
            int res1 = maxCover01(arr);
            int res2 = maxCover02(arr);
            if(res1 != res2){
                System.out.println(res1 + " " + res2);
                System.out.println("mistake!!!");
                break;
            }
        }
        System.out.println("success!!!");
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }
}
