package algorithms_java.class19;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_BobDie {

    public static int saveRate(int N, int M, int r, int c, int k) {
        int save = waysSave(N, M, r, c, k);
        int die = waysDie(N, M, r, c, k);

        return save / (save + die);
    }

    public static int waysSave(int N, int M, int r, int c, int k) {
        if (r < 0 || r > N || c < 0 || c > M) {
            return 0;
        }

        if (k == 0) {
            return 1;
        }

        int ways = waysSave(N, M, r + 1, c, k - 1);
        ways += waysSave(N, M, r - 1, c, k - 1);
        ways += waysSave(N, M, r, c - 1, k - 1);
        ways += waysSave(N, M, r, c + 1, k - 1);

        return ways;
    }


    public static int waysDie(int N, int M, int r, int c, int k) {
        if (r < 0 || r > N || c < 0 || c > M) {
            return 1;
        }

        if (k == 0) {
            return 0;
        }

        int ways = waysDie(N, M, r + 1, c, k - 1);
        ways += waysDie(N, M, r - 1, c, k - 1);
        ways += waysDie(N, M, r, c - 1, k - 1);
        ways += waysDie(N, M, r, c + 1, k - 1);

        return ways;
    }

    public static int dpSave(int N, int M, int r, int c, int k) {

        int[][][] dp = new int[N][M][k + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int z = 1; z <= k; z++) {
            dp[0][0][z] = dp[1][0][z - 1] + dp[0][1][z - 1];
            dp[N - 1][0][z] = dp[N - 2][0][z - 1] + dp[N - 1][1][z - 1];
            dp[0][M - 1][z] = dp[0][M - 2][z - 1] + dp[1][M - 1][z - 1];
            dp[N - 1][M - 1][z] = dp[N - 2][M - 1][z - 1] + dp[N - 1][M - 2][z - 1];

            for (int i = 1; i < N - 1; i++) {
                dp[i][0][z] = dp[i][1][z - 1] + dp[i + 1][0][z - 1];
                dp[i][M - 1][z] = dp[i + 1][M - 1][z - 1] + dp[i][M - 2][z - 1];
            }

            for (int i = 1; i < M - 1; i++) {
                dp[0][i][z] = dp[1][i][z - 1] + dp[0][i + 1][z - 1];
                dp[N - 1][i][z] = dp[N - 2][i][z - 1] + dp[N - 1][i + 1][z - 1];
            }

            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    int ways = dp[i + 1][j][z - 1];
                    ways += dp[i - 1][j][z - 1];
                    ways += dp[i][j + 1][z - 1];
                    ways += dp[i][j - 1][z - 1];
                    dp[i][j][z] = ways;
                }
            }
        }

        return dp[r][c][k];
    }

    public static int dpDie(int N, int M, int r, int c, int k) {
        int[][][] dp = new int[N][M][k + 1];

        for (int z = 0; z <= k; z++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][z] = -1;
                }
            }
        }

        return process(dp, N, M, r, c, k);
    }


    public static int process(int[][][] dp, int N, int M, int r, int c, int k) {
        if (dp[r][c][k] != -1) {
            return dp[r][c][k];
        }

        if (r < 0 || r > N || c < 0 || c > M) {
            dp[r][c][k] = 1;
            return 1;
        }

        if (k == 0) {
            dp[r][c][k] = 0;
            return 0;
        }

        int ways = waysDie(N, M, r + 1, c, k - 1);
        ways += waysDie(N, M, r - 1, c, k - 1);
        ways += waysDie(N, M, r, c - 1, k - 1);
        ways += waysDie(N, M, r, c + 1, k - 1);

        dp[r][c][k] = ways;
        return ways;
    }
}
