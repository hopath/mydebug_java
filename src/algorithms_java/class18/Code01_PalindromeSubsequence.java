package algorithms_java.class18;

import algorithms_java.class17.Code04_LongestCommonSubsequence;
import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */

//范围尝试模型
public class Code01_PalindromeSubsequence {

    public int longestPalindromeSubseq04(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int N = s.length();
        int[][] dp = new int[N][N];
        char[] chars = s.toCharArray();

        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
        }

        for (int i = 2; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                int r = j;
                int c = j + i;

                int p1 = dp[r + 1][c - 1];
                int p2 = dp[r][c - 1];
                int p3 = dp[r + 1][c];
                int p4 = chars[r] == chars[c] ? 2 + dp[r + 1][c - 1] : 0;

                dp[r][c] = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
            }
        }

        return dp[0][N - 1];
    }

    public int longestPalindromeSubseq03(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process03(s.toCharArray(), 0, s.length() - 1);
    }

    public int process03(char[] chars, int L, int R) {
        if (L == R) {
            return 1;
        }

        if (L == R - 1) {
            return chars[L] == chars[R] ? 2 : 1;
        }

        //左右都不是最长公共子序列的开头或结尾
        int p1 = process03(chars, L + 1, R - 1);

        //左边不可能是开头，右边可能是结尾
        int p2 = process03(chars, L, R - 1);

        //左边可能是开头，右边不可能是结尾
        int p3 = process03(chars, L + 1, R);

        //左右是开头或结尾
        int p4 = chars[L] == chars[R] ? (2 + process03(chars, L + 1, R - 1)) : 0;

        return Math.max(p1, Math.max(p2, Math.max(p3, p4)));
    }


    //求一个字符串中最长回文子序列
    public int longestPalindromeSubseq02(String s) {
        //将字符串逆序
        String reverseString = reverseString(s);
        //两个字符串的公共子序列
        Code04_LongestCommonSubsequence commonSubsequence =
                new Code04_LongestCommonSubsequence();

        return commonSubsequence.longestCommonSubsequence02(s, reverseString);
    }

    private String reverseString(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            swap(chars, left++, right--);
        }

        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }


    public int longestPalindromeSubseq01(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return process01(chars, "", 0);
    }

    public static int process01(char[] chars, String path, int index) {
        if (index == chars.length) {
            if (isPalindrome(path)) {
                return path.length();
            }
            return 0;
        }

        int p1 = process01(chars, path, index + 1);

        int p2 = process01(chars, path + chars[index], index + 1);

        return Math.max(p1, p2);
    }

    private static boolean isPalindrome(String path) {
        if (path == null || path.length() == 0) {
            return false;
        }

        int left = 0;
        int right = path.length() - 1;
        char[] chars = path.toCharArray();

        while (left < right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }

        return true;
    }
}




