package algorithms_java.class19;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 张志伟
 * @version 1.0
 */

/*
arr是货币数组,其中的值都是正数。再给定一个正数aim。
每个值都认为是一张货币,
认为值相同的货币没有任何不同,
返回组成aim的方法数
例如:arr={1,2,1,1,2,1,2}, aim=4
方法:1+1+1+1、1+1+2、2+2
一共就3种方法,所以返回3
 */
public class Code04_CoinsWaySameValueSamePapper {

    static class Info {
        int[] coins; //面值
        int[] zs; //张数

        public Info(int[] coins, int[] zs) {
            this.coins = coins;
            this.zs = zs;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (counts.containsKey(arr[i])) {
                Integer value = counts.get(arr[i]);
                counts.put(arr[i], value + 1);
            } else {
                counts.put(arr[i], 1);
            }
        }

        int N = counts.size();
        int[] coins = new int[N];
        int[] zs = new int[N];
        int index = 0;
        Set<Integer> keySet = counts.keySet();
        for (Integer i : keySet) {
            coins[index] = i;
            zs[index++] = counts.get(i);
        }

        return new Info(coins, zs);
    }


    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Info info = getInfo(arr);

        return process(info.coins, info.zs, 0, aim);
    }

    public static int process(int[] coins, int[] zs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int z = 0; z * coins[index] <= rest && z <= zs[index]; z++) {
            ways += process(coins, zs, index + 1, rest - z * coins[index]);
        }

        return ways;
    }

    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zs = info.zs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] +
                        pick(dp, index + 1, rest - zs[index] * coins[index]) -
                        pick(dp, index + 1, rest - (zs[index] + 1) * coins[index]);
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
}
