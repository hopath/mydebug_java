package algorithms_java.class17;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author 张志伟
 * @version 1.0
 */
//求要拼成一个指定字符串，需要贴纸的最小张数(贴纸可剪至最小单位，且可任意组合)
//剪枝
public class Code03_StickersToSpellWord {

    @Test
    public void test() {
        String[] strs = new String[]{"abc", "csd", "dbc"};
        System.out.println(minStick(strs, "dsca"));
        System.out.println(minStack03(strs, "dsca"));
    }

    public static int minStick02(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }

        if (stickers == null || stickers.length == 0) {
            return -1;
        }

        //对strs进行词频统计
        int N = stickers.length;
        int[][] sticks = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char ch : chars) {
                sticks[i][ch - 'a']++;
            }
        }

        int ans = process02(sticks, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process02(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }

        //对target进行词频统计
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char ch : target) {
            tcounts[ch - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] stick = stickers[i];
            if (stick[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - stick[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process02(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStick(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }

        if (stickers == null || stickers.length == 0) {
            return -1;
        }

        int ans = process01(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process01(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (String first : stickers) {
            String rest = minus(target, first);
            if (target.length() != rest.length()) {
                min = Math.min(min, process01(stickers, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
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


        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                builder.append((char) (i + 'a'));
                count[i]--;
            }
        }

        return builder.toString();
    }


    public static int minStack03(String[] stickers, String target) {
        //对strs进行词频统计
        int N = stickers.length;
        int[][] sticks = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char ch : chars) {
                sticks[i][ch - 'a']++;
            }
        }

        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process03(sticks, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public static int process03(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        //对target进行词频统计
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char ch : target) {
            tcounts[ch - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] stick = stickers[i];
            if (stick[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - stick[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process02(stickers, rest));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }
}
