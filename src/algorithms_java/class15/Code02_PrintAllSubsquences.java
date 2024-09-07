package algorithms_java.class15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_PrintAllSubsquences {

    @Test
    public void test() {
        List<String> ans = subs("abcdef");
        for (String str : ans) {
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

}
