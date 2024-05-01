package algorithms_java.class06;


/**
 * @author 张志伟
 * @version 1.0
 */

/*
    归并排序为我们提供了左右两组有序的情况，使比较的成本降低
    一般有两个位置可以利用
    a. 在归并过程中加入O(1)的操作
    b. 在归并外加入O(N)的操作
 */
public class Code04_BiggerThanRightTwice {

    public static void main(String[] args) {
        int time = 1000;
        int maxSize = 100;
        int maxValue = 100;

        for(int i = 0; i < time; i++){
            int arr[] = Code01_MergeSort.generateRandomArray(maxSize, maxValue);
            int arr1[] = Code01_MergeSort.copyArray(arr);

            int res1 = Code04_BiggerThanRightTwice.biggerThanRightTwice(arr);
            int res2 = Code04_BiggerThanRightTwice.biggerThanRightTwice2(arr1);

            System.out.println(res1 +"\t||\t" + res2);

            if(res1 != res2){
                System.out.println("mistake!!!");
                return;
            }
        }
        System.out.println("nice!!!");
    }
    
    public static int biggerThanRightTwice(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right){
        if(left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);

        return process(arr, left, mid) + process(arr, mid + 1, right)
                + merge(arr, left, right, mid);
    }

    private static int merge(int[] arr, int left, int right, int mid){
        int N = right - left + 1;   int help[] = new int[N];
        int p1 = left;  int p2 = mid + 1;
        int k = 0;

        //此时左右两边都有序，以实现O(N)完成计数
        int res = getNum(arr, left, right, mid);

        while(p1 <= mid && p2 <= right){
            if(arr[p1] < arr[p2]){
                help[k++] = arr[p1++];
            }else {
                help[k++] = arr[p2++];
            }
        }
        while (p1 <= mid){
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

    private static int getNum(int[] arr, int left, int right, int mid){
        int res = 0;
        int p1 = left;  int p2 = mid + 1;

        /*
            两种方式：此时不包含p2的位置  [mid + 1, p2)
                      或者包含p2的位置   (mid, p2 + 1]
         */
        while(p1 <= mid){
            while (p2 <= right && arr[p2] * 2 < arr[p1]){
                p2++;
            }
            res += p2 - (mid + 1);
            p1++;
        }
        return res;
    }

    public static int biggerThanRightTwice2(int[] arr){
        int res = 0;

        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] * 2 < arr[j]){
                    res++;
                }
            }
        }

        return res;
    }

}
