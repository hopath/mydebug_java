package algorithms_java.class17;

import com.sun.xml.internal.bind.v2.util.StackRecorder;
import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_StickersToSpellWord {

    @Test
    public void test() {
        String[] strs = new String[]{"abc", "csd", "dbc"};
        System.out.println(minStick(strs, "dscbda"));
        System.out.println(minStick02(strs, "dscbda"));
    }


    public static int minStick02(String[] strs, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }

        if (strs == null || strs.length == 0) {
            return -1;
        }

        //对strs进行词频统计
        int N = strs.length;
        int[][] sticks = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = strs[i].toCharArray();
            for (char ch : chars) {
                sticks[i][ch - 'a']++;
            }
        }

        return process02(sticks, target);
    }

    public static int process02(int[][] sticks, String target) {
        if (target.length() == 0) {
            return 0;
        }

        //对target进行词频统计
        char[] targetCharArray = target.toCharArray();
        int[] tcounts = new int[26];
        for (char ch : targetCharArray) {
            tcounts[ch - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sticks.length; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            if (sticks[i][targetCharArray[0] - 'a'] > 0) {
                for (int j = 0; j < 26; j++) {
                    int nums = sticks[i][j] - tcounts[j];
                    for (int k = 0; k < nums; k++) {
                        stringBuffer.append((char) (j + 'a'));
                    }
                }
            }
            String rest = stringBuffer.toString();
            if(rest.length() != target.length()){
                Math.min(min, process02(sticks, rest));
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }

    public static int minStick(String[] strs, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }

        if (strs == null || strs.length == 0) {
            return -1;
        }

        return process01(strs, target);
    }

    public static int process01(String[] strs, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (String first : strs) {
            String rest = minus(first, target);
            if (target.length() != rest.length()) {
                min = Math.min(min, process01(strs, rest));
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }


    public static String minus(String target, String first) {
        char[] targetCharArray = target.toCharArray();
        char[] firstCharArray = first.toCharArray();
        int[] count = new int[26];

        for (char ch : targetCharArray) {
            count[ch - 'a']++;
        }

        for (char ch : firstCharArray) {
            count[ch - 'a']--;
        }


        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                stringBuffer.append((char) (i + 'a'));
                count[i]--;
            }
        }

        return stringBuffer.toString();
    }


}
