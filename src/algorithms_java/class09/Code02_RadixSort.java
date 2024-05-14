package algorithms_java.class09;

/**
 * @author 张志伟
 * @version 1.0
 */

/**
 * 1. 操作数组对象不要搞错
 * 2. 循环变量不要搞错
 * 3. j++还是j--注意一下
 * 4. 在写代码的时候脑海中要有图，或者画出来
 */

/**
 * 为什么从右向左遍历，对于每一位，我们知道其所在范围（该位有多个相同的数）, 而右边的数一定大于或等于这个范围的数
 * 所以将将最右边的数放在该位置的最右边
 */
public class Code02_RadixSort {

    public static void main(String[] args) {
        int arr[] = {12, 10, 78, 22, 11};
        radixSort(arr);
        printArray(arr);
    }
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    private static int maxDigit(int[] arr){
        int digit = 0; int tmp = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[0] < arr[i]){
                arr[0] = arr[i];
            }
        }

        while(arr[0] / 10 != 0){
            digit++;
            arr[0] /= 10;
        }
        arr[0] = tmp;
        return digit + 1;
    }

    private static int getDigitValue(int value, int digit){
        int res = 0;
        for(int i = 0; i < digit; i++){
            res = value % 10;
            value /= 10;
        }
        return res;
    }
    //@param d 最大值的位数
    public static void radixSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int[] count = new int[10];
        int[] help = new int[10];
        int[] out = new int[arr.length];
        int digit = maxDigit(arr);

        for(int i = 0; i < digit; i++){
            //算出位上十进制数的个数
            for(int j = 0; j < arr.length; j++){
                int value = getDigitValue(arr[j], i + 1);
                count[value]++;
            }
            //算出小于等于位上十进制数的个数
            help[0] = count[0];
            for(int j = 1; j < count.length; j++){
                help[j] = help[j - 1] + count[j];
            }
            /*
                不需要help数组，直接操作count
                for(int j = 1; j < count.length; j++){
                    count[j] = count[j - 1] + count[j];
                }
             */
            //对每个数根据位上十进制数进行排位

            for(int j = arr.length - 1; j >= 0; j--){
                int digitValue = getDigitValue(arr[j], i + 1);
                out[help[digitValue] - 1] = arr[j];
                help[digitValue]--;
            }

            for(int j = 0; j < arr.length; j++){
                arr[j] = out[j];
            }
            for(int j = 0; j < count.length; j++){
                count[j] = 0;
            }
        }
    }
}
