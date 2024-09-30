/**
 * @author 张志伟
 * @version 1.0
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
        String s = "abcd";
        String s1 = reverseString(s);
        System.out.println(s1);
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
}
