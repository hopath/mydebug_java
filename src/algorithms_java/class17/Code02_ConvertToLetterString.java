package algorithms_java.class17;

import org.junit.jupiter.api.Test;

import javax.naming.PartialResultException;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_ConvertToLetterString {


    @Test
    public void test() {
        System.out.println(nums1("2342452346838"));
        System.out.println(nums2("2342452346838"));
    }

    public static int nums1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    public static int nums2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return dp(chars);
    }

    /**
     * 返回方法数
     *
     * @param chars 字符数组
     * @param i     当前位置
     * @return
     */
    public static int process(char[] chars, int i) {
        if (i == chars.length) {
            return 1;
        }

        if (chars[i] == 0) {
            return 0;
        }

        int ways = process(chars, i + 1);

        if (i + 1 < chars.length && (chars[i + 1] - '0') * 10 + (chars[i] - '0') < 27) {
            ways = ways + process(chars, i + 2);
        }

        return ways;
    }


    public static int dp(char[] chars) {
        int[] dp = new int[chars.length + 1];
        dp[dp.length - 1] = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            if (chars[i] == '0') {
                dp[i] = 0;
            }
            dp[i] = dp[i + 1];
            if (i + 1 < chars.length && (chars[i + 1] - '0') * 10 + (chars[i] - '0') < 27) {
                dp[i] = dp[i] + dp[i + 2];
            }
        }
        return dp[0];
    }
}
