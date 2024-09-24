package algorithms_java.class17;

import com.sun.xml.internal.bind.v2.util.StackRecorder;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_StickersToSpellWord {

    public static int minStick(String[] strs, String target){
        if(target == null || target.length() == 0){
            return 0;
        }

        if(strs == null || strs.length == 0){
            return -1;
        }

        return process01(strs, target);
    }

    public static int process01(String[] strs, String target){
        if(target.length() == 0){
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for(String first : strs){
            String rest = minus(first, target);
            if (target.length() != rest.length()) {
                min = Math.min(min, process01(strs, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    public static String minus(String target, String first){
        char[] targetCharArray = target.toCharArray();
        char[] firstCharArray = first.toCharArray();
        int[] count = new int[26];

        for(char ch : targetCharArray){
            count[ch - 'a']++;
        }

        for(char ch : firstCharArray){
            count[ch - 'a']--;
        }


        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0; i < count.length; i++){
            while(count[i] > 0){
                stringBuffer.append((char)(i + 'a'));
                count[i]--;
            }
        }

        return stringBuffer.toString();
    }
}
