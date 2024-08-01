package algorithms_java.class12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author 张志伟
 * @version 1.0
 */

//给定一个字符串数组，可以自由结合(每个字符都要用)，返回字典序最小的哪一个字符串
public class Code01_LowestLexicography {

    private static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    //strs中所有字符串全排列，返回所有可能的结果
    public static String lowestLexicography01(String[] strs) {
        return process(strs).first();
    }

    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<String>();

        if(strs.length == 0){
            ans.add("");
            return ans;
        }

        for(int i = 0; i < strs.length; i++){
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);

            //拿到除first以外的全排列
            TreeSet<String> next = process(nexts);
            for(String cur : next){
                ans.add(first + cur);
            }
        }
        return ans;
    }

    public static String[] removeIndexString(String[] strs, int i){
        String[] res = new String[strs.length - 1];
        int j = 0;
        for(int k = 0; k < strs.length; k++){
            if(k != i){
                res[j] = strs[k];
                j++;
            }
        }
        return res;
    }

    /*
        贪心，前一个字符串在前与后一个拼接，前一个字符串在后与后一个拼接，小的放前面
        得到的字符数组拼接而成就是最小字典序
     */


    public static String lowestLexicography02(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());

        String res = "";
        for (String s : strs) {
            res += s;
        }

        return res;
    }



    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }


    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestLexicography01(arr1).equals(lowestLexicography02(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

