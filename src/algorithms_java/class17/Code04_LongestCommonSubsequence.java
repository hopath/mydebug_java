package algorithms_java.class17;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_LongestCommonSubsequence {

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
            int p1 = process01(str1, str2, i, j - 1);

            int p2 = process01(str1, str2, i - 1, j);

            //int p3 = str1[i] == str2[j] ? (1 + process01(str1, str2, i - 1, j - 1)) : process01(str1, str2, i - 1, j - 1);
            int p3 = str1[i] == str2[j] ? (1 + process01(str1, str2, i - 1, j - 1)) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }
}
