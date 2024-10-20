package algorithms_java.class19;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */

/*
arr是面值数组,其中的值都是正数且没有重复。再给定一个正数aim。
每个值都认为是一种面值,且认为张数是无限的。
返回组成aim的方法数
例如:arr={1,2}, aim=4
方法如下:1+1+1+1、1+1+2、2+2
一共就3种方法,所以返回3
 */
//与src/algorithms_java/class17/Code03_StickersToSpellWord对比
public class Code03_CoinWayNotLimit {

    @Test
    public void test() {
        int[] arr = {2, 1, 3};
        System.out.println(ways01(arr, 11));
        System.out.println(dp01(arr, 11));
    }



    //由dp01严格表结构推出的优化
    public static int dp02(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = pick(dp, index, rest - arr[index]) + dp[index + 1][rest];
            }
        }
        return dp[0][aim];
    }

    public static int pick(int[][] dp, int i, int j) {
        if (j < 0) {
            return 0;
        }
        return dp[i][j];
    }

    public static int dp01(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int z = 0; z * arr[index] <= rest; z++) {
                    ways += dp[index + 1][rest - z * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int ways01(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process01(arr, 0, aim);
    }

    public static int process01(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int z = 0; z * arr[index] <= rest; z++) {
            ways += process01(arr, index + 1, rest - z * arr[index]);
        }

        return ways;
    }


    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, aim);
    }

    public static int process(int[] arr, int rest) {
        if (rest < 0) {
            return 0;
        }

        if (rest == 0) {
            return 1;
        }

        int ways = 0;
        for (int i = 0; i < arr.length; i++) {
            ways += process(arr, rest - arr[i]);
        }
        return ways;
    }
}
