package algorithms_java.class18;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
/*
请同学们自行搜索或者想象一个象棋的棋盘,
然后把整个棋盘放入第一象限,棋盘的最左下角是(0,0)位置
那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
给你三个 参数x,y,k
返回“马”从(0,0)位置出发,必须走k步
最后落在(xy)上的方法数有多少种?
 */
public class Code02_HoursJump {

    @Test
    public void test(){
        System.out.println(ways01(3,4, 5));
        System.out.println(ways02(3,4, 5));
    }

    public static int ways02(int x, int y, int k) {
        return dp(x, y, k);
    }

    public static int dp(int xp, int yp, int k) {
        int dp[][][] = new int[9][10][k + 1];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                dp[i][j][0] = (i == xp && j == yp) ? 1 : 0;
            }
        }

        for (int n = 1; n < k + 1; n++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    int ways = dp[i - 1][j + 2][n];
                    ways += dp[i + 1][j + 2][n];
                    ways += dp[i - 1][j - 2][n];
                    ways += dp[i + 1][j - 2][n];
                    ways += dp[i - 2][j + 1][n];
                    ways += dp[i + 2][j + 1][n];
                    ways += dp[i - 2][j - 1][n];
                    ways += dp[i + 2][j - 1][n];
                    dp[i][j][n] = ways;
                }
            }
        }

        return dp[xp][yp][k];
    }

    public static int ways01(int x, int y, int k) {
        return process01(x, y, 0, 0, k);
    }

    public static int process01(int xp, int yp, int x, int y, int k) {
        if (x < 0 || y < 0 || x > 9 || y > 8) {
            return 0;
        }

        if (k == 0) {
            return (x == xp && y == yp) ? 1 : 0;
        }

        int ways = process01(xp, yp, x - 1, y + 2, k--);
        ways += process01(xp, yp, x + 1, y + 2, k--);
        ways += process01(xp, yp, x - 1, y - 2, k--);
        ways += process01(xp, yp, x + 1, y - 2, k--);
        ways += process01(xp, yp, x - 2, y + 1, k--);
        ways += process01(xp, yp, x + 2, y + 1, k--);
        ways += process01(xp, yp, x - 2, y - 1, k--);
        ways += process01(xp, yp, x + 2, y - 1, k--);

        return ways;
    }
}
