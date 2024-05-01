package algorithms_java.class06;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code06_QuickSort {
    @Test
    public void test06(){
        int arr[] = {-2, -1, -3, 3, 0 };
        Code06_QuickSort.QuickSortNon(arr, 0, arr.length - 1);
        //System.out.println(res[0] + " " + res[1]);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    //分成 >= 和 < 或者 <= 和 > 两组
    private static void process(int[] arr, int val){
        int leftMin = -1; int p = 0;
        while (p < arr.length){
            if(arr[p] <= val){
                int tmp = arr[leftMin + 1];
                arr[leftMin + 1] = arr[p];
                arr[p++] = tmp;
                leftMin++;
            }else{
                p++;
            }
        }
    }
    //分成 > 和 == 和 < 三个部分
    private static void process02(int[] arr, int val){
        int leftMin = - 1; int rightMax = arr.length;
        int p1 = 0;

        while(p1 < rightMax){
            if(arr[p1] < val){
                int tmp1 = arr[leftMin + 1];
                arr[leftMin + 1] = arr[p1];
                arr[p1++] = tmp1;
                leftMin++;
            }else if(p1 > val){
                int tmp2 = arr[rightMax - 1];
                arr[rightMax - 1] = arr[p1];
                arr[p1] = tmp2;
                rightMax--;
            }else{
                p1++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    /*
        分成 > 和 == 和 < 三个部分
        并返回等于区间的左右边界
     */
    private static int[] pvt(int[] arr, int left, int right){
        if(left > right){
            return new int[]{-1, -1};
        }
        if(left == right){
            return new int[]{left, right};
        }
        int less = left - 1; int more = right;
        int p = left; int val = arr[right];

        while(p < more){
            if(arr[p] < val){
                swap(arr, ++less, p++);
            }else if(arr[p] > val){
                //因是从右边交换而来，此时还不能确定交换来的数的情况，故不跳过
                swap(arr, --more, p);
            }else{
                p++;
            }
        }
        swap(arr, more, right);

        return new int[]{less + 1, more};
    }
    public static void quickSort(int[] arr, int left, int right){
        if(left == right || left > right){
            return;
        }

        //让命中最好和最坏成为一个概率事件，数学证明最终会使复杂度收敛于NlogN
        swap(arr, left + ((int)(Math.random() * (right - left))), right);

        int res[] = pvt(arr, left, right);
        quickSort(arr, left, res[0] - 1);
        quickSort(arr, res[1] + 1, right);
    }

    private static class Op{
        int l;
        int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Op{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
    public static void QuickSortNon(int[] arr, int left, int right){
        if(arr == null || left == right || left > right){
            return;
        }
        Stack<Op> opStack = new Stack<>();
        Op op = new Op(left, right);
        opStack.push(op);

        while(!opStack.empty()){
            Op pop = opStack.pop();
            int L = pop.l; int R = pop.r;

            if(L < R){
                swap(arr,  L + ((int)(Math.random() * (R - L))), R);
                int res[] = pvt(arr, L, R);

                //记录之后要排的范围
                Op op1 = new Op(L, res[0] - 1);
                Op op2 = new Op(res[1] + 1, R);

                opStack.push(op1);
                opStack.push(op2);
            }
        }
    }
}
