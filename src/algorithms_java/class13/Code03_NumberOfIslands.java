package algorithms_java.class13;

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

    public static int getNumberOfIslands02(int board[][]) {
        ArrayList<Doat> doatArrayList = new ArrayList<>();
        int r = board.length;
        int c = board[0].length;
        Doat[][] doats = new Doat[r][c];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    Doat doat = new Doat();
                    doats[i][j] = doat;
                    doatArrayList.add(doat);
                }
            }
        }

        Code01_UnionFind.UnionFind unionFind =
                new Code01_UnionFind.UnionFind<Doat>(doatArrayList);

        for (int i = 1; i < c; i++) {
            if (doats[i - 1][0] != null) {
                unionFind.union(doats[i][0], doats[i - 1][0]);
            }
        }

        for (int i = 1; i < r; i++) {
            if (doats[0][i - 1] != null) {
                unionFind.union(doats[0][i], doats[0][i - 1]);
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (doats[i - 1][j] != null) {
                    unionFind.union(doats[i][j], doats[i - 1][j]);
                }
                if (doats[i][j - 1] != null) {
                    unionFind.union(doats[i][j], doats[i][j - 1]);
                }
            }
        }

        return unionFind.size();
    }
}
