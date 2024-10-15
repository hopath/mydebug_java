package algorithms_java.class18;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 张志伟
 * @version 1.0
 */

/*
给定一个数组arr,arr[i]代表第i号咖啡机泡一杯咖啡的时间
给定一个正数N,表示N个人等着咖啡机泡咖啡,每台咖啡机只能轮流泡咖啡
只有一台洗咖啡机,一次只能洗一个杯子,时间耗费a,洗完才能洗下一杯
每个咖啡杯也可以自己挥发干净,时间耗费b,咖啡杯可以并行挥发
假设所有人拿到咖啡之后立刻喝干净,
返回从开始等到所有咖啡机变干净的最短时间
三个参数:int[]arr、int N, int a、int b
 */
public class Code03_Coffee {

    @Test
    public void test(){
        int[] arr = {1, 3, 2, 5};
        System.out.println(minTime(arr, 8, 4, 9));
        System.out.println(minTimeDp(arr, 8, 4, 9));
    }

    static class Machine {
        int workPoint;
        int workTime;

        public Machine(int workPoint, int workTime) {
            this.workPoint = workPoint;
            this.workTime = workTime;
        }
    }

    static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.workPoint + o1.workTime) - (o2.workPoint + o2.workTime);
        }
    }

    public static int[] drinkMins(int[] arr, int n) {
        PriorityQueue<Machine> machines = new PriorityQueue<>(new MachineComparator());
        //每个人喝完所需最短时间
        int[] drinks = new int[n];

        for (int i = 0; i < arr.length; i++) {
            machines.add(new Machine(0, arr[i]));
        }

        for (int i = 0; i < n; i++) {
            Machine poll = machines.poll();
            poll.workPoint += poll.workTime;
            drinks[i] = poll.workPoint;
            machines.add(poll);
        }

        return drinks;
    }

    public static int minTimeDp(int[] arr, int n, int wash, int air) {
        int[] drinks = drinkMins(arr, n);
        int ml = maxWashTimeLine(drinks, wash, 0);
        int index = drinks.length;
        int[][] dp = new int[index + 1][ml + 1];

        for (int i = index - 1; i >= 0; i--) {
            for(int j = 0; j < ml + 1; j++){
                //使用洗咖啡杯机器的情况
                int curWashTime1 = Math.max(drinks[i], j) + wash;
                //该情况不存在，没有必要填
                if(curWashTime1 > ml){
                    continue;
                }
                int nextWashTime1 = dp[i + 1][curWashTime1];
                int washTime1 = Math.max(curWashTime1, nextWashTime1);

                //使用自然挥发的情况
                int curWashTime2 = drinks[i] + air;
                int nextWashTime2 = dp[i + 1][j];
                int washTime2 = Math.max(curWashTime2, nextWashTime2);

                dp[i][j] = Math.min(washTime1, washTime2);
            }
        }

        return dp[0][0];
    }

    public static int maxWashTimeLine(int[] drinks, int wash, int washLine) {
        for (int i = 0; i < drinks.length; i++) {
            washLine = Math.max(drinks[i], washLine) + wash;
        }
        return washLine;
    }

    public static int minTime(int[] arr, int n, int a, int b) {
        int[] drinks = drinkMins(arr, n);
        return process01(drinks, a, b, 0, 0);
    }

    public static int process01(int[] drinks, int wash, int air, int index, int washLine) {
        if (index == drinks.length) {
            return 0;
        }

        //使用洗咖啡杯机器的情况
        int curWashTime1 = Math.max(drinks[index], washLine) + wash;
        int nextWashTime1 = process01(drinks, wash, air, index + 1, curWashTime1);
        int washTime1 = Math.max(curWashTime1, nextWashTime1);

        //使用自然挥发的情况
        int curWashTime2 = drinks[index] + air;
        int nextWashTime2 = process01(drinks, wash, air, index + 1, washLine);
        int washTime2 = Math.max(curWashTime2, nextWashTime2);

        return Math.min(washTime1, washTime2);
    }

}
