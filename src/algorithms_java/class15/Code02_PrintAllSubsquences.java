package algorithms_java.class15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_PrintAllSubsquences {

    @Test
    public void test() {
//        List<String> ans = subs("abcccdef");
//        for (String str : ans) {
//            System.out.println(str);
//        }
        HashSet<String> strings = subsNoRepeat("abccdef");
        for(String str: strings){
            System.out.println(str);
        }
    }


    public static List<String> subs(String str) {
        char[] chars = str.toCharArray();
        String path = "";
        ArrayList<String> ans = new ArrayList<>();

        return process01(chars, 0, ans, path);
    }

    /**
     * @param chars 要生成所有子序列的字符数组
     * @param index 当前在字符数组的位置
     * @param ans   未确定的所有子序列情况
     * @param path  已经确定的字符组成的字符串
     * @return
     */
    public static List<String> process01(char[] chars, int index, ArrayList<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            return ans;
        }

        //该字符不选取
        process01(chars, index + 1, ans, path);
        //该字符被选
        process01(chars, index + 1, ans, path + chars[index]);

        return ans;
    }


    public static HashSet<String> subsNoRepeat(String s) {
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();

        return process02(chars, 0, set, "");
    }


    public static HashSet<String> process02(char[] chars, int index, HashSet<String> set, String path) {
        if (index == chars.length) {
            set.add(path);
            return set;
        }

        process02(chars, index + 1, set, path);

        process02(chars, index + 1, set, path + chars[index]);

        return set;
    }


}
