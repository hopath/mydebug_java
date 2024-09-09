package algorithms_java.class15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_PrintAllPermutations {

    @Test
    public void test() {
        String str = "acc";
        ArrayList<String> ans = new ArrayList<>();

        List<String> permutation = permutation02(str, ans);
        for (String s : permutation) {
            System.out.println(s);
        }
    }

    public static List<String> permutation02(String str, ArrayList<String> ans) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char[] charArray = str.toCharArray();

        return process03(charArray, 0, ans);
    }


    public static List<String> process02(char[] chars, int index, ArrayList<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return ans;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process02(chars, index + 1, ans);
            swap(chars, index, i);
        }
        return ans;
    }

    //去重版
    public static List<String> process03(char[] chars, int index, ArrayList<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return ans;
        }

        //默认都是false
        boolean[] visit = new boolean[256];
        for (int i = index; i < chars.length; i++) {
            //已经交换过的相同字符不重复交换
            if (!visit[chars[i]]) {
                visit[chars[i]] = true;
                swap(chars, index, i);
                process03(chars, index + 1, ans);
                swap(chars, index, i);
            }
        }
        return ans;
    }


    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static List<String> permutation(String str, ArrayList<String> ans) {
        if (str == null && str.length() == 0) {
            return ans;
        }

        char[] charArray = str.toCharArray();
        ArrayList<Character> chars = new ArrayList<>();
        for (char c : charArray) {
            chars.add(c);
        }

        return process(chars, "", ans);
    }

    public static List<String> process(ArrayList<Character> chars, String path, ArrayList<String> ans) {
        if (chars.isEmpty()) {
            ans.add(path);
            return ans;
        }

        for (int i = 0; i < chars.size(); i++) {
            Character c = chars.get(i);
            chars.remove(c);
            process(chars, path + c, ans);
            //恢复现场
            chars.add(i, c);
        }

        return ans;
    }

}
