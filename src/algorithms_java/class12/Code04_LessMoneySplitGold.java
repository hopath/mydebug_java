package algorithms_java.class12;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author 张志伟
 * @version 1.0
 */

/*
一块金条切成两半,是需要花费和长度数值一样的铜板的。
比如长度为20的金条,不管怎么切,都要花费20个铜板。一群人想整分整块金条,怎么分最省铜板?

例如,给定数组{10,20,30},代表一共三个人,整块金条长度为60,金条要分成10,20,30三个部分。

如果先把长度60的金条分成10和50,花费60;再把长度50的金条分成20和30,花费50;一共花费110铜板。
但如果先把长度60的金条分成30和30,花费60;再把长度30金条分成10和20,花费30;一共花费90铜板。

输入一个数组,返回分割的最小代价。
 */
public class Code04_LessMoneySplitGold {

    public static int getLessMoneySplitGold01(int[] arr) {
        if(arr.length == 1){
            return arr[0];
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                int[] next = TowMergeToOne(arr, i, j);
                int lastMoney = getLessMoneySplitGold01(next);
                min = Math.min(min, arr[i] + arr[j] + lastMoney);
            }
        }

        return min;
    }

    public static int[] TowMergeToOne(int[] arr, int i, int j){
        int ans[] = new int[arr.length - 1];
        int p = 0;
        for(int k = 0; k < arr.length; k++){
            if(k != i && k != j){
                ans[p] = arr[k];
                p++;
            }
        }
        ans[p] = arr[i] + arr[j];

        return ans;
    }

    public static int getLessMoneySplitGold02(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr == null) {
            return 0;
        }

        int min = 0, p = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }

        while (heap.size() > 1) {
            Integer p1 = heap.poll();
            Integer p2 = heap.poll();
            p = p1 + p2;
            min += p;
            heap.add(p);
        }

        return min;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            for(int k = 0; k < arr.length; k++){
                System.out.print(arr[k] + " ");
            }
            System.out.println();
            if (getLessMoneySplitGold01(arr) != getLessMoneySplitGold02(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
