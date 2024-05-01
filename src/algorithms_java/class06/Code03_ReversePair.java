package algorithms_java.class06;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_ReversePair {

    public static void main(String[] args) {
        int time = 1000;
        int maxValue = 50;
        int maxSize = 50;

        for(int i = 0; i < time; i++){
            int arr1[] = Code02_SmallSum.generateRandomArray(maxSize, maxValue);
            int arr2[] = Code02_SmallSum.copyArray(arr1);

            Code02_SmallSum.printArray(arr1);
            int res1 = Code03_ReversePair.reversePair(arr1);
            int res2 = Code03_ReversePair.reversePair2(arr2);

            System.out.println(res1 + "\t" + res2);

            if(res1 != res2){
                System.out.println("mistake!!!");
                return;
            }

            System.out.println("success!!!");
        }
    }

    @Test
    public void test(){
        int arr[] = {1, -2, -1};
        int i = Code03_ReversePair.reversePair(arr);
        System.out.println(i);
    }

    private static int merge(int arr[], int left, int right, int mid){
        int N = right - left + 1;
        int help[] = new int[N];     int k = 0;
        int p1 = left;  int p2 = mid + 1;
        int cnt = 0;

        while (p1 <= mid && p2 <= right){
            if(arr[p1] <= arr[p2]){
                help[k++] = arr[p1++];
            }else{
                cnt += mid - p1 + 1;
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

        return cnt;
    }
    private static int process(int arr[], int left, int right){
        if(left == right){
            return 0;
        }

        int mid = left + ((right - left) >> 1);

        return process(arr, left, mid) + process(arr, mid + 1, right)
                + merge(arr, left, right, mid);
    }
    public static int reversePair(int arr[]){
        if(arr == null || arr.length < 2){
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    public static int reversePair2(int arr[]){
        if(arr == null || arr.length < 2){
            return 0;
        }

        int cnt = 0;

        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[i]){
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
