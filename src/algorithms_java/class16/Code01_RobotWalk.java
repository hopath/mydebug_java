package algorithms_java.class16;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_RobotWalk {

    public static int ways1(int N, int start, int aim, int K) {
        return process01(start, K, aim, N);
    }

    /**
     *
     * @param cur 机器人当前位置
     * @param rests 剩下的步数
     * @param aim 目标位置
     * @param N 一共有多少个位置
     * @return 返回机器人总路线数
     */
    public static int process01(int cur, int rests, int aim, int N) {
        if (rests == 0) {
            return cur == aim ? 1 : 0;
        }

        if (cur - 1 < 0) {
            return process01(cur + 1, rests - 1, aim, N);
        }

        if (cur + 1 > N - 1)  {
            return process01(cur - 1, rests - 1, aim, N);
        }

        return process01(cur + 1, rests - 1, aim, N) +
                process01(cur - 1, rests - 1, aim, N);
    }
}
