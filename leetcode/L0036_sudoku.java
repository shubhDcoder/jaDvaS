public class L0036_sudoku {
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
        System.out.println(new L0036_sudoku().isValidSudoku(grid));
    }

    public int[] rowBits = new int[9];
    public int[] colBits = new int[9];
    public int[][] blockBits = new int[3][3];

    public boolean isValidSudoku(char[][] board) {
        boolean result = false;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != '.') {
                    int mask = (1 << ((int)(board[i][j] - '0')));
                    if((rowBits[i] & mask) == 0 && (colBits[j] & mask) == 0 && (blockBits[i / 3][j / 3] & mask) == 0) {
                        rowBits[i] ^= mask;
                        colBits[j] ^= mask;
                        blockBits[i / 3][j / 3] ^= mask;
                    } else return false;
                }
            }
        }
        
        return true;
    }
}