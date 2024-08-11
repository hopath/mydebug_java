package algorithms_java.class13;

/**
 * @author 张志伟
 * @version 1.0
 */
public class Code03_NumberOfIslands {

    public static int getNumberOfIslands(char[][] board){
        int landNum = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '1'){
                    landNum++;
                    infect(board, i, j);
                }
            }
        }

        return landNum;
    }

    private static void infect(char[][] board, int i, int j){
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != '1'){
            return;
        }
        board[i][j] = '2';
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }
}
