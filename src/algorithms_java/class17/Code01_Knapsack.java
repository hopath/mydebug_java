package algorithms_java.class17;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || w.length != v.length || v == null || v.length == 0) {
            return 0;
        }

        return process01(w, v, 0, bag);
    }

    public static int process01(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            return -1;
        }

        if (index == w.length) {
            return 0;
        }
        int p1 = process01(w, v, index + 1, bag);

        int p2 = 0;
        //若后续无效则返回-1
        int next = process01(w, v, index + 1, bag - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);
    }

}
