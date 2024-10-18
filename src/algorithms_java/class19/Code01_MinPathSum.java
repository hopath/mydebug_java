package algorithms_java.class19;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code01_MinPathSum {

    public static int minSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        return process(matrix, 0, 0);
    }

    public static int process(int[][] matrix, int x, int y) {
        int M = matrix.length;
        int N = matrix[0].length;

        if (x == M - 1 && y == N - 1) {
            return matrix[x][y] + Math.min(process(matrix, x + 1, y), process(matrix, x, y + 1));
        }

        if(x == 0 || y == 0){
            return matrix[x][y];
        }

        int p1 = process(matrix, x + 1, y);
        int p2 = process(matrix, x, y + 1);

        return Math.min(p1, p2) + matrix[x][y];
    }
}
