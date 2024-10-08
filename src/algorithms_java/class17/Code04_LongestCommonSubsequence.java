package algorithms_java.class17;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */

//样本尝试模型
public class Code04_LongestCommonSubsequence {
    @Test
    public void test(){
        System.out.println(longestCommonSubsequence02("abcde", "ace"));
    }


    //返回两个字符串相同字符组成的最大子字符串(不需要连续)的个数
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] str1CharArray = text1.toCharArray();
        char[] str2CharArray = text2.toCharArray();

        return process01(str1CharArray, str2CharArray, text1.length() - 1, text2.length() - 1);
    }

    public static int process01(char[] str1, char[] str2, int i, int j) {
        if (str1[i] == 0 && str2[j] == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            }
            return process01(str1, str2, i, j - 1);
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            }
            return process01(str1, str2, i - 1, j);
        } else {
            //i一定不选, j可能选
            int p1 = process01(str1, str2, i, j - 1);
            //i可能选, j一定不选
            int p2 = process01(str1, str2, i - 1, j);

            //两个都选
            //int p3 = str1[i] == str2[j] ? (1 + process01(str1, str2, i - 1, j - 1)) : process01(str1, str2, i - 1, j - 1);
            int p3 = str1[i] == str2[j] ? (1 + process01(str1, str2, i - 1, j - 1)) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public int longestCommonSubsequence02(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int N = text1.length();
        int M = text2.length();
        int[][] dp = new int[N][M];

        return dp(str1, str2, dp);
    }

    public static int dp(char[] str1, char[] str2, int[][] dp) {
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }

        for(int i = 1; i < str2.length; i++){
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }

        for(int i = 1; i < str1.length; i++){
            for(int j = 1; j < str2.length; j++){
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }
}
