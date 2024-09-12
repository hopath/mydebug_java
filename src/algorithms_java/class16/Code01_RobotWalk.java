package algorithms_java.class16;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_RobotWalk {

    @Test
    public void test() {

        System.out.println(ways1(5, 1, 3, 6));
        System.out.println(ways3(5, 1, 3, 6));

    }


    public static int ways1(int N, int start, int aim, int K) {
        return process01(start, K, aim, N);
    }


    /**
     * @param cur   机器人 当前位置
     * @param rests 剩下的步数
     * @param aim   目标位置
     * @param N     一共有多少个位置
     * @return 返回机器人总路线数
     */
    public static int process01(int cur, int rests, int aim, int N) {
        if (rests == 0) {
            return cur == aim ? 1 : 0;
        }

        if (cur == 1) {
            return process01(cur + 1, rests - 1, aim, N);
        }

        if (cur == N) {
            return process01(cur - 1, rests - 1, aim, N);
        }

        return process01(cur + 1, rests - 1, aim, N) +
                process01(cur - 1, rests - 1, aim, N);
    }


    public static int ways2(int N, int start, int aim, int K) {
        int dp[][] = new int[N + 1][K + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return process02(start, K, aim, N, dp);
    }

    public static int process02(int cur, int rests, int aim, int N, int[][] dp) {
        //缓存表若cur与rests已经跑过则直接返回，不用重新算了
        if (dp[cur][rests] != -1) {
            return dp[cur][rests];
        }

        int ans = 0;

        if (rests == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process01(cur + 1, rests - 1, aim, N);
        } else if (cur == N) {
            ans = process01(cur - 1, rests - 1, aim, N);
        } else {
            ans = process01(cur + 1, rests - 1, aim, N) +
                    process01(cur - 1, rests - 1, aim, N);
        }

        //将算完的结果放入缓冲表
        dp[cur][rests] = ans;

        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {
        int[][] dp = new int[N + 1][K + 1];
        process03(N, K, aim, dp);
        return dp[start][K];
    }

    public static void process03(int N, int K, int aim, int[][] dp) {
        //按列遍历
        for (int rests = 0; rests <= K; rests++) {
            for (int cur = 1; cur <= N; cur++) {
                if (rests == 0) {
                    if (cur == aim) {
                        dp[cur][0] = 1;
                    } else {
                        dp[cur][0] = 0;
                    }
                } else if (cur == 1) {
                    dp[cur][rests] = dp[cur + 1][rests - 1];
                } else if (cur == N) {
                    dp[cur][rests] = dp[cur - 1][rests - 1];
                } else {
                    dp[cur][rests] = dp[cur + 1][rests - 1] + dp[cur - 1][rests - 1];
                }
            }
        }
    }
}
