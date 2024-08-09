package algorithms_java.class12;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code06_Light {

    public static int getMinLight(String road) {
        if(road == null){
            return 0;
        }
        int i = 0;
        int light = 0;
        char[] charArray = road.toCharArray();
        //注意内部越界问题，重复代码抽出
        while (i < charArray.length) {
            if (charArray[i] == '.') {
                light++;
                if(i + 1 == charArray.length){
                    break;
                }
                if (charArray[i + 1] == '.') {
                    //light++;
                    i += 3;
                } else if (charArray[i + 1] == '*') {
                    //light++;
                    i += 2;
                }
            }else{
                i++;
            }
        }
        return light;
    }

}
