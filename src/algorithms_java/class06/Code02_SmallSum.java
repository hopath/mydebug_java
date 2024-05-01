package algorithms_java.class06;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */

public class Code02_SmallSum {

    @Test
    public void testCode02(){
        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int res1 = Code02_SmallSum.smallSum(arr1);
            int res2 = Code02_SmallSum.smallSum02(arr2);
            System.out.println(res1 + " $ " + res2);
            if(res1 != res2){
                System.out.println("mistake!!!!");
                return;
            }
        }
        System.out.println("success!!!");
    }

    private static int merge(int arr[], int left, int right, int mid){
        int p1 = left;
        int p2 = mid + 1;

        int N = right - left + 1;
        int k = 0;
        int help[] = new int[N];

        int res = 0;

        while(p1 <= mid && p2 <= right){
            if(arr[p1] < arr[p2]){
                int num = right - p2 + 1;
                res += num * arr[p1];
                help[k++] = arr[p1++];
            }else{
                help[k++] = arr[p2++];
            }
        }

        while(p1 <= mid){
            help[k++] = arr[p1++];
        }
        while(p2 <= right){
            help[k++] = arr[p2++];
        }

        for(int i = 0; i < N; i++){
            arr[left++] = help[i];
        }

        return res;
    }
    private static int process(int arr[], int left, int right){
        if(left == right){
            return 0;
        }

        int mid = left + ((right - left) >> 1);

        return  process(arr, left, mid) +
                process(arr, mid + 1, right) +
                merge(arr, left, right, mid);

    }

    /**
     * 小和问题：去找右边比自己大的数的个数，数组中的数会被分成两组，
     * 左边在归并时检测右组还剩下的个数，即大于左组数的个数（小于左侧的已经归并完成了），
     *右组归并时不检测（因为对于右组来说，没有右边的数），
     *
     * 一组求完后该组就被排好序了，那么排序会影响求和吗？
     * 不会！因为对于下一组来说上一组的左右都属于左组（或右组）
     *
     * 排序的意义：确定打大于左边的数的个数的操作是O(1)
     */

    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }

        int left = 0;
        int right = arr.length - 1;

        return process(arr, left, right);
    }

    public static int smallSum02(int[] arr){
        int res = 0;
        if(arr == null || arr.length < 2){
            return 0;
        }

        int p2 = 1;

        while(p2 < arr.length){
            for(int i = 0; i < p2; i++){
                if(arr[i] < arr[p2]){
                    res += arr[i];
                }
            }
            p2++;
        }

        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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
}



