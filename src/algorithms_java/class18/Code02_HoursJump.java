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
        System.out.println(ways01(3,4, 3));
        System.out.println(ways02(3,4, 3));
    }

    public static int ways02(int x, int y, int k) {
        return dp(x, y, k);
    }

    public static int dp(int xp, int yp, int k) {
        int dp[][][] = new int[9][10][k + 1];

        dp[yp][xp][0] = 1;

        for (int rest = 1; rest < k + 1; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp,y - 1, x + 2, rest - 1);
                    ways += pick(dp, y + 1, x + 2, rest - 1);
                    ways += pick(dp, y - 1, x - 2, rest - 1);
                    ways += pick(dp,  y + 1, x - 2, rest - 1);
                    ways += pick(dp,  y - 2, x + 1, rest - 1);
                    ways += pick(dp,  y + 2, x + 1, rest - 1);
                    ways += pick(dp,  y - 2, x - 1, rest - 1);
                    ways += pick(dp, y + 2, x - 1, rest - 1);
                    dp[y][x][rest] = ways;
                }

            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int y, int x, int rest){
        if (x < 0 || y < 0 || x > 9 || y > 8) {
            return 0;
        }
        return dp[y][x][rest];
    }

    public static int ways01(int x, int y, int k) {
        return pic(x, y, 0, 0, k);
    }

    public static int pic(int xp, int yp, int x, int y, int k) {
        if (x < 0 || y < 0 || x > 9 || y > 8) {
            return 0;
        }

        if (k == 0) {
            return (x == xp && y == yp) ? 1 : 0;
        }

        int ways = pic(xp, yp, x - 1, y + 2, k - 1);
        ways += pic(xp, yp, x + 1, y + 2, k - 1);
        ways += pic(xp, yp, x - 1, y - 2, k - 1);
        ways += pic(xp, yp, x + 1, y - 2, k - 1);
        ways += pic(xp, yp, x - 2, y + 1, k - 1);
        ways += pic(xp, yp, x + 2, y + 1, k - 1);
        ways += pic(xp, yp, x - 2, y - 1, k - 1);
        ways += pic(xp, yp, x + 2, y - 1, k - 1);

        return ways;
    }

}
