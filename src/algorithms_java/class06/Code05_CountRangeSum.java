package algorithms_java.class06;

import org.junit.jupiter.api.Test;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code05_CountRangeSum {


    public static void main(String[] args) {
        int[] nums = {-2,5,-1};
        System.out.println(countRangeSum(nums, -2, 2));
        System.out.println(countRangeSum02(nums, -2, 2));
        System.out.println(countRangeSum03(nums, -2, 2));
    }

    public static  int countRangeSum(int[] nums, int lower, int upper) {
        int k = 0;  int cnt = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length; j++){
                //确定边界时要确保是以下标为边界还是以个数为边界
                if(j + i > nums.length - 1){
                    break;
                }
                long sum = 0;
                int m = i;
                int n = j;
                while(m + 1 > 0){
                    //int n = j;    定义迭代变量的时候一定要注意更新的位置
                    sum += nums[n++];
                    //i--;      外层循环用的变量不要在循环内改变
                    m--;
                }
                if(sum >= lower && sum <= upper){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int countRangeSum02(int[] nums, int lower, int upper){
        long preSum[] = new long[nums.length];

        int m = 0;  int n = 0;  int cnt = 0;   long sum = 0;
        preSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(i != 0){
                    sum = preSum[j] - preSum[i - 1];
                }else{
                    sum = preSum[j];
                }
                if(sum >= lower && sum <= upper){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int countSum(long[] sums, int left, int right, int mid, int lower, int upper){
        int p1 = left; int p2 = left; int sum = 0;
        for(int i = mid + 1; i <= right; i++){
            long nl = sums[i] - upper;
            long nu = sums[i] - lower;

            while(p2 <= mid){
                if(sums[p2] > nu){
                    break;
                }
                if(sums[p1] < nl){
                    p1++;
                }
                if(sums[p2] <= nu){
                    p2++;
                }
            }
            //注意前后指针都越界后才能保证，此时右组后面的下标对应的左组已经没有符合范围的数了
            while(p1 <= mid){
                if(sums[p1] >= nl){
                    break;
                }
                if(sums[p1] < nl){
                    p1++;
                }
            }

            sum += (p2 - p1);
        }

        return sum;
    }

    private static int merge(long[] sums, int left, int right, int mid, int lower, int upper){
        int N = right - left + 1;
        long[] help = new long[N];
        int p1 = left; int p2 = mid + 1; int k = 0;

        int res = countSum(sums, left, right, mid, lower, upper);

        while(p1 <= mid && p2 <= right){
            if(sums[p1] < sums[p2]){
                help[k++] = sums[p1++];
            }else{
                help[k++] = sums[p2++];
            }
        }
        while(p1 <= mid){
            help[k++] = sums[p1++];
        }
        while(p2 <= right){
            help[k++] = sums[p2++];
        }

        for(int i = 0; i < N; i++){
            sums[left++] = help[i];
        }

        return res;
    }
    private static int process(long[] sums, int left, int right, int lower, int upper){
        if(left == right){
            if(sums[left] >= lower && sums[left] <= upper){
                return 1;
            }else{
                return 0;
            }
        }

        int mid = left + ((right - left) >> 1);

        int leftNum = process(sums, left, mid, lower, upper);
        int rightNum = process(sums, mid + 1, right, lower, upper);
        int mergeNum = merge(sums, left, right, mid, lower, upper);

        return  leftNum + rightNum + mergeNum;
    }

    /*
        本题的关键在于，以每个下标作为区间的结尾，
        通过遍历检查从0到该下标前面的所有下标位置的区间和是否在[sum-upper, sum-lower]
        (sum为每个结尾到0的区间和)范围中，若在则后面区间(到结尾区间为止)
        在[upper, lower]范围内并进行计数，在我们将每个下标作为区间结尾后，按照上述逻辑所有区间都被验证过了，
        在这个过程中，我们可以发现问题可以转换为对于右组的每一个下标，左组有几个下标对应的数(即0到该下标的区间和)
        在[sum-upper, sum-lower]范围内,注意此时讨论的数组是求和数组(将0到指定下标的区间和)，
        这样我们就可用归并排序来减少比对次数，
        同时有序可保证在遍历找左组的数有几个在[sum-upper, sum-lower]范围内时，迭代是不回退的，
        因为在右组右边的数比左边大在迭代的过程中，左组所要符合的左右边界时递增的。
     */
    public static int countRangeSum03(int[] nums, int lower, int upper){
        if(nums == null){
            return 0;
        }
        if(nums.length < 2){
            /*
            此时左右指针相等即只有一个数了，
            此时只要判定该数是否在[lower,upper]之间就行，
            特别的其也完成了，左组不存在的情况。
             */
            if(nums[0] >= lower && nums[0] <= upper){
                return 1;
            }else{
                return 0;
            }
        }
        int left = 0; int right = nums.length - 1;
        long sums[] = new long[nums.length];

        sums[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            sums[i] = sums[i - 1] + nums[i];
        }

        return process(sums, left, right, lower, upper);
    }
    @Test
    public void test03(){
        int[] nums = {1, 2, 3};
        //countRangeSum03(nums, 0, 0);
    }
}

