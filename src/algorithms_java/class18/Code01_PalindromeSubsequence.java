package algorithms_java.class18;

import algorithms_java.class17.Code04_LongestCommonSubsequence;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_PalindromeSubsequence {
    //求一个字符串中最长回文子序列
    public int longestPalindromeSubseq02(String s) {
        //将字符串逆序
        String reverseString = reverseString(s);
        //两个字符串的公共子序列
        Code04_LongestCommonSubsequence commonSubsequence =
                new Code04_LongestCommonSubsequence();

        return commonSubsequence.longestCommonSubsequence02(s, reverseString);
    }

    private String reverseString(String s){
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while(left < right){
            swap(chars, left++, right--);
        }

        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j){
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




