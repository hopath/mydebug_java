package algorithms_java.class17;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_Knapsack {

    @Test
    public void test() {
        int[] w = {3, 4, 8, 9, 7};
        int[] v = {4, 6, 9, 1, 3};

        System.out.println(getMaxValue(w, v, 20));
        System.out.println(dp(w, v, 20));
    }

    //背包一定, 返回最大值
    public static int getMaxValue(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || w.length != v.length || v == null || v.length == 0) {
            return 0;
        }

        return process01(w, v, 0, bag);
    }

    public static int process01(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }

        if (index == w.length) {
            return 0;
        }
        int p1 = process01(w, v, index + 1, rest);

        int p2 = 0;
        //若后续无效则返回-1
        int next = process01(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);
    }


    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || w.length != v.length || v == null || v.length == 0) {
            return 0;
        }

        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }

                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
