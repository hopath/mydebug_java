package algorithms_java.class13;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_NumberOfIslands {
    public static int getNumberOfIslands01(char[][] board) {
        int landNum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    landNum++;
                    infect(board, i, j);
                }
            }
        }

        return landNum;
    }

    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != '1') {
            return;
        }

        board[i][j] = '2';
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }

    static class Doat {

    }

    @Test
    public void test() {
        char board[][] =
                {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        getNumberOfIslands02(board);
    }


    public static int getNumberOfIslands02(char board[][]) {
        ArrayList<Doat> doatArrayList = new ArrayList<>();
        int r = board.length;
        int c = board[0].length;
        Doat[][] doats = new Doat[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == '1') {
                    Doat doat = new Doat();
                    doats[i][j] = doat;
                    doatArrayList.add(doat);
                }
            }
        }

        Code01_UnionFind.UnionFind unionFind =
                new Code01_UnionFind.UnionFind<Doat>(doatArrayList);

        for (int i = 1; i < r; i++) {
            if (doats[i - 1][0] != null && doats[i][0] != null) {
                unionFind.union(doats[i][0], doats[i - 1][0]);
            }
        }

        for (int i = 1; i < c; i++) {
            if (doats[0][i - 1] != null && doats[0][i] != null) {
                unionFind.union(doats[0][i], doats[0][i - 1]);
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (doats[i][j] != null) {
                    if (doats[i - 1][j] != null) {
                        unionFind.union(doats[i][j], doats[i - 1][j]);
                    }
                    if (doats[i][j - 1] != null) {
                        unionFind.union(doats[i][j], doats[i][j - 1]);
                    }
                }
            }
        }

        return unionFind.size();
    }

    public static int getNumberOfIslands03(char board[][]) {
        int r = board.length;
        int c = board[0].length;

        UnionFind unionFind = new UnionFind(board, r * c);

        for (int i = 1; i < r; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                unionFind.union(getIndex(i - 1, 0), getIndex(i, 0));
            }
        }

        for (int i = 1; i < c; i++) {
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                unionFind.union(getIndex(0, i - 1), getIndex(0, i));
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (board[i][j] == '1') {
                    int index = getIndex(i, j);
                    if (board[i - 1][j] == '1') {
                        unionFind.union(index, getIndex(i - 1, j));
                    }
                    if (board[i][j - 1] == '1') {
                        unionFind.union(index, getIndex(i, j - 1));
                    }
                }
            }
        }

        return unionFind.sites;
    }

    private static int getIndex(int r, int c) {
        return r * c + c;
    }

    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sites;

        public UnionFind(char arr[][], int N) {
            int r = arr.length;
            int c = arr[0].length;

            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sites = 0;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (arr[i][j] == '1') {
                        int index = getIndex(i, j);
                        parent[index] = index;
                        size[index] = 1;
                        sites++;
                    }
                }
            }
        }

        public int find(int i) {
            int p = 0;
            while (parent[i] != i) {
                help[p++] = i;
                i = parent[i];
            }

            for (p--; p >= 0; p--) {
                parent[help[p]] = i;
            }

            return i;
        }

        public void union(int i, int j) {
            int p1 = find(i);
            int p2 = find(j);

            if (p1 != p2) {
                int large = size[p1] > size[p2] ? p1 : p2;
                int small = large == p1 ? p2 : p1;
                parent[small] = large;
                size[large] = size[p1] + size[p2];
                sites--;
            }
        }
    }
}
