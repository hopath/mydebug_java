package algorithms_java.class16;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_CardsInLine {
    @Test
    public void test() {
        int[] arr = {2, 9, 7, 8, 4, 3};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

    //两人博弈抽卡
    public static int win3(int[] arr) {
        int[][] Fdp = new int[arr.length][arr.length];
        int[][] Gdp = new int[arr.length][arr.length];

        for (int i = 0; i < Fdp.length; i++) {
            Fdp[i][i] = arr[i];
        }

        for (int i = 1; i < Fdp.length; i++) {
            for (int j = i; j < Fdp.length; j++) {
                int start = j - i;
                int end = j;
                Fdp[start][end] =
                        Math.max(arr[start] + Gdp[start + 1][end], arr[end] + Gdp[start][end - 1]);
                Gdp[start][end] =
                        Math.min(Fdp[start + 1][end], Fdp[start][end - 1]);
            }
        }

        return Math.max(Fdp[0][arr.length - 1], Gdp[0][arr.length - 1]);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[][] dp1 = new int[arr.length][arr.length];
        int[][] dp2 = new int[arr.length][arr.length];

        for (int i = 0; i < dp1.length; i++) {
            for (int j = 0; j < dp1[0].length; j++) {
                dp1[i][j] = -1;
                dp2[i][j] = -1;
            }
        }
        int p1 = f2(arr, 0, arr.length - 1, dp1, dp2);
        int p2 = g2(arr, 0, arr.length - 1, dp1, dp2);

        return Math.max(p1, p2);
    }


    public static int f2(int[] arr, int start, int end, int[][] dp1, int[][] dp2) {
        if (dp1[start][end] != -1) {
            return dp1[start][end];
        }

        int res = 0;

        if (start == end) {
            res = arr[start];
        } else {
            int p1 = arr[start] + g2(arr, start + 1, end, dp1, dp2);
            int p2 = arr[end] + g2(arr, start, end - 1, dp1, dp2);
            res = Math.max(p1, p2);
        }

        dp1[start][end] = res;
        return res;
    }


    public static int g2(int[] arr, int start, int end, int[][] dp1, int[][] dp2) {
        if (dp2[start][end] != -1) {
            return dp2[start][end];
        }

        int res = 0;

        if (start != end) {
            int p1 = f2(arr, start + 1, end, dp1, dp2);
            int p2 = f2(arr, start, end - 1, dp1, dp2);

            res = Math.min(p1, p2);
        }

        dp2[start][end] = res;
        return res;
    }


    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);

        return Math.max(first, second);
    }

    //先手拿最大值
    public static int f1(int[] arr, int start, int end) {
        if (start == end) {
            return arr[start];
        }

        int p1 = arr[start] + g1(arr, start + 1, end);
        int p2 = arr[end] + g1(arr, start, end - 1);

        return Math.max(p1, p2);
    }

    //后手只能拿最小值
    public static int g1(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }

        //先手拿了左侧牌的情况
        int p1 = f1(arr, start + 1, end);
        int p2 = f1(arr, start, end - 1);

        return Math.min(p1, p2);
    }


}
