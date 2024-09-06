package algorithms_java.class15;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_Hanoi {

    public static void main(String[] args) {
        hanoi2(2, "a", "b", "c");
    }


    public static void hanoi2(int n, String from, String to, String other) {
        if (n > 0) {
            func(n, from, to, other);
        }
    }

    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }

        func(n - 1, from, other, to);
        System.out.println("move " + n + " from " + from + " to " + to);
        func(n - 1, other, to, from);
    }

    public static void hanoi1(int n) {
        leftToRight(n);
    }

    public static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }

        leftToMid(n - 1);
        System.out.println("move " + n + " from left to right");
        midToRight(n - 1);
    }

    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("move " + n + " from left to mid");
        rightToMin(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void rightToMin(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }

        midToRight(n - 1);
        System.out.println("move " + n + " from right to mid");
        rightToLeft(n - 1);
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMin(n - 1);
        System.out.println("move " + n + " from right to left");
        midToLeft(n - 1);
    }
}
