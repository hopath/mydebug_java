package algorithms_java.class16;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code02_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);

        return Math.max(first, second);
    }

    public static int f1(int[] arr, int start, int end) {
        if (start == end) {
            return arr[start];
        }

        int p1 = arr[start] + g1(arr, start + 1, end);
        int p2 = arr[end] + g1(arr, start, end - 1);

        return Math.max(p1, p2);
    }


    public static int g1(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }

        int p1 = g1(arr, start + 1, end);
        int p2 = g1(arr, start, end - 1);

        return Math.min(p1, p2);
    }
}
