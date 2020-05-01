import java.util.*;

public class L0037_sudoku {
    public static void main(String args[]) {
        char[][] grid =  {
                            {'5','3','.','.','7','.','.','.','.'},
                            {'6','.','.','1','9','5','.','.','.'},
                            {'.','9','8','.','.','.','.','6','.'},
                            {'8','.','.','.','6','.','.','.','3'},
                            {'4','.','.','8','.','3','.','.','1'},
                            {'7','.','.','.','2','.','.','.','6'},
                            {'.','6','.','.','.','.','2','8','.'},
                            {'.','.','.','4','1','9','.','.','5'},
                            {'.','.','.','.','8','.','.','7','9'}
                        };

        L0037_sudoku obj = new L0037_sudoku();
        displayMatrix(grid);

        System.out.println("after filling");

        obj.solveSudoku(grid);

        obj.fillSudoku(0, grid);

        displayMatrix(grid);
    }

    public int[] rowBits = new int[9];
    public int[] colBits = new int[9];
    public int[][] blockBits = new int[3][3];
    List<Integer> filledWithZeros = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != '.') {
                    int mask = (1 << ((int)(board[i][j] - '0')));
                    rowBits[i] |= mask;
                    colBits[j] |= mask;
                    blockBits[i / 3][j / 3] |= mask;
                } else 
                    filledWithZeros.add(i * 9 + j);
            }
        }
    }

    public boolean fillSudoku(int index, char[][] board) {
        if(index == filledWithZeros.size()) {
            return true;
        }

        boolean result = false;
        int listIndex = filledWithZeros.get(index);
        int i = listIndex / 9;
        int j = listIndex % 9;

        for(int number = 1; number < 10; number++) {
            int mask = (1 << number);
            if((rowBits[i] & mask) == 0 && (colBits[j] & mask) == 0 && (blockBits[i / 3][j / 3] & mask) == 0) {
                board[i][j] = (char)(number + '0');
                rowBits[i] ^= mask;
                colBits[j] ^= mask;
                blockBits[i / 3][j / 3] ^= mask;

                result = result || fillSudoku(index + 1, board);

                if(result) return true;
                else {
                    rowBits[i] ^= mask;
                    colBits[j] ^= mask;
                    blockBits[i / 3][j / 3] ^= mask;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    public static void displayMatrix(char[][] board) {
        for(int i = 0; i < 9; i++) {
            if(i % 3 == 0) System.out.println("-------------------------------");
            for(int j = 0; j <= 9; j++) {
                if(j % 3 == 0) System.out.print("|");
                if(j != 9)
                    System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
    }

}