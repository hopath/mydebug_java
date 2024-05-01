package algorithms_java.class06;

import algorithms_java.class07.Code02_Heap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_MergeSort {
    //递归
    private static void merge(int arr[], int left, int right){
        //需要多少开多少
        int res[] = new int[right -  left + 1];
        int mid = left + ((right - left) >> 1);
        //注意k在执行后面代码后变化了
        int k = 0;
        int begin1 = left;
        int begin2 = mid + 1;

        while(begin1 <= mid && begin2 <= right){
            if(arr[begin1] < arr[begin2]){
                res[k++] = arr[begin1++];
            }else{
                res[k++] = arr[begin2++];
            }
        }
        while (begin1 <= mid){
            res[k++] = arr[begin1++];
        }
        while(begin2 <= right){
            res[k++] = arr[begin2++];
        }

        //由于k的变化需要新的重0开始的变量
        for(int i = 0; i < res.length; i++){
            arr[left + i] = res[i];
        }
    }

    public static void mergeSort(int[] arr, int left, int right){
        //注意边界
        if(arr == null || arr.length < 2){
            return;
        }

        process(arr, left, right);
    }

    public static void process(int[] arr, int left, int right){
        if(left == right){
            return;
        }

        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);

        merge(arr, left, right);
    }



    ///////////////////test///////////////////////
    @Test
    public void testCode01(){
        int testTime = 500;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            printArray(arr2);
            mergeSort(arr1, 0, arr1.length - 1);
            Code02_Heap.heapSort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static void mergeSort03(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        int N = arr.length;
        int help[] = new int[N];
        int step = 1;
        int left = 0;
        int right = 0;
        int rightLimit = 0;

        while (step < N){
            for(left = 0; left < N; left += 2 * step){
                int k = 0;
                right = left + step;
                if(right < N){
                    //调整右边界
                    if(right + step > N){
                        rightLimit = N;
                    }else{
                        rightLimit  = right + step;
                    }

                    //merge
                    int p1 = left;
                    int p2 = right;
                    while(p1 < left + step && p2 < rightLimit){
                        if(arr[p1] < arr[p2]){
                            help[k++] = arr[p1++];
                        }else{
                            help[k++] = arr[p2++];
                        }
                    }
                    while(p1 < left + step){
                        help[k++] = arr[p1++];
                    }
                    while(p2 < rightLimit){
                        help[k++] = arr[p2++];
                    }

                    //将排好的数写会数组
                    k = 0;
                    for(int i = left; i < rightLimit; i++){
                        arr[i] = help[k++];
                    }
                }
            }
            if(step > N/ 2){
                break;
            }
            step *= 2;
        }
    }
    //迭代
    public static void mergeSort02(int arr[]){
        if(arr == null || arr.length < 2){
            return;
        }

        int[] help = new int[arr.length];
        int step = 1;
        int left = 0;
        int right = 0;
        int rightLimit = 0;

        while(step < arr.length){
            for(left = 0; left < arr.length; left += 2 * step){
                //每次都有进行复位
                int k = 0;
                right = left + step;
                if(!(right > arr.length)){
                    int p1 = left;
                    int p2 = right;
                    if(right + step > arr.length){
                        rightLimit = arr.length;
                    }else {
                        rightLimit = right + step;
                    }
                    while(p1 < left + step && p2 < rightLimit){
                        if(arr[p1] < arr[p2]){
                            help[k++] = arr[p1++];
                        }else {
                            help[k++] = arr[p2++];
                        }
                    }
                    while(p1 < left + step){
                        help[k++] = arr[p1++];
                    }
                    while(p2 < rightLimit){
                        help[k++] = arr[p2++];
                    }
                    k = 0;
                    for(int i = left; i < rightLimit; i++){
                        //注意下标和对应的数组
                        arr[i] = help[k++];
                    }
                }
            }
            step *= 2;
        }
    }

    @Test
    public void test02Code01(){
        int arr[] = {-1, 3, 4, 9, 7};
        mergeSort03(arr);
        printArray(arr);
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

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        //先列出显然不符合条件的
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
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


