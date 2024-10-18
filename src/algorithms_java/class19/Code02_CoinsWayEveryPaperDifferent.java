package algorithms_java.class19;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_CoinsWayEveryPaperDifferent {

    @Test
    public void test() {
        int[] arr = {1, 2, 1, 3, 3, 1};
        System.out.println(ways(arr, 7));
        System.out.println(dp(arr, 7));
    }

    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }

        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        return process(arr, index + 1, rest)
                + process(arr, index + 1, rest - arr[index]);
    }


    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + pick(dp, i + 1, j - arr[i]);
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
