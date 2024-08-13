package algorithms_java.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code04_NumberOfIslandsII {
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        char[][] board = new char[m][n];
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = 0;
            }
        }

        for(int i = 0; i < positions.length; i++){
            board[positions[i][1]][positions[i][2]] = '1';
            int numberOfIslands02 = Code03_NumberOfIslands.getNumberOfIslands02(board);
            res.add(numberOfIslands02);
        }

        return res;
    }
}
