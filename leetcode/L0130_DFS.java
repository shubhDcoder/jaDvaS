import java.util.*;

public class L0130_DFS {
    public static void main(String args[]) {
        char[][] array = new char[][] {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };

        for(char[] arr : array) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println();
        new L0130_DFS().solve(array);

        for(char[] arr : array) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public void solve(char[][] board) {
    if(board.length == 0) return;
        for(int i = 0; i < board[0].length; i++) {
            if(board[0][i] == 'O') {
                callDfs(0, i, board);
            }
        }
        for(int i = 0; i < board[0].length; i++) {
            if(board[board.length - 1][i] == 'O') {
                callDfs(board.length - 1, i, board);
            }
        }
        for(int i = 0; i < board.length; i++) {
            if(board[i][0] == 'O') {
                callDfs(i, 0, board);
            }
        }
        for(int i = 0; i < board.length; i++) {
            if(board[i][board[0].length - 1] == 'O') {
                callDfs(i, board[0].length - 1, board);
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '@') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public void callDfs(int row, int col, char[][] board) {
        if(board[row][col] == 'X' || board[row][col] == '@') return;

        board[row][col] = '@';
        if(row + 1 < board.length) callDfs(row + 1, col, board);
        if(col + 1 < board[0].length) callDfs(row, col + 1, board);
        if(row - 1 >= 0) callDfs(row - 1, col, board);
        if(col - 1 >= 0) callDfs(row, col - 1, board);
    }
}