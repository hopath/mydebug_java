package algorithms_java.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_NumberOfIslandsII {
    //生成遍历
    public static List<Integer> numIslands20(int m, int n, int[][] positions) {
        char[][] board = new char[m][n];
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < positions.length; i++) {
            board[positions[i][1]][positions[i][2]] = '1';
            int numberOfIslands02 = Code03_NumberOfIslands.getNumberOfIslands02(board);
            res.add(numberOfIslands02);
        }

        return res;
    }

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {

    }

    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sites;
        private int col;

        public UnionFind(int m, int n) {
            int N = m * n;
            col = n;
            parent = new int[N];
            size = new int[N];
            help = new int[N];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int k = 0;
            while (parent[i] != i) {
                i = parent[i];
                help[k++] = i;
            }

            for (k--; k >= 0; k--) {
                parent[k] = i;
            }

            return i;
        }



        private void union(int r, int c) {
        }
    }
}
