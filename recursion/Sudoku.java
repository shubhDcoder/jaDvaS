import java.util.List;
import java.util.ArrayList;

public class Sudoku {
    public static int[][] board = new int[][] {
            {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
    };
    public static int board_row = 9;
    public static int board_col = 9;

    public static int[] row_filled = new int[board_row];
    public static int[] col_filled = new int[board_col];
    public static int[][] matrix_filled = new int[3][3];
    public static List<Integer> zerosAt = new ArrayList<>();

    public static void main(String args[]) {

        markHeading(" SUDOKU 9X9 ");

        printLine("Sudoku with basic approach");
        displayMatrix(true);
        fillPreDefinedValue();
        // displayPreFilledMatrix();
        fillSudoku(0);
        
        printLine("Sudoku with bits approach");
        displayMatrix(true);
        fillSudoku_01(0);

        printLine("Sudoku with bits approach with arrayList");
        // cleanState();
        fillArrayListWithIndexesOfZeros();
        displayMatrix(true);
        fillSudoku_02(0);
    }
    
    public static void fillArrayListWithIndexesOfZeros() {
        for(int i = 0; i < board_row; i++) {
            for( int j = 0; j < board_col; j++) {
                if(board[i][j] == 0) zerosAt.add(i * 9 + j);
            }
        }
    }

    public static boolean fillSudoku_02(int index) {
        if(index == zerosAt.size()) {
            displayMatrix(false);
            return true;
        }

        boolean result = false;

        int ind = zerosAt.get(index);
        int row = ind / 9;
        int col = ind % 9;

        for(int i = 1; i <= 9; i++) {
            int mask = (1 << i);
            if((mask & row_filled[row]) == 0 && (mask & col_filled[col]) == 0 || (matrix_filled[row / 3][col / 3]) == 0) {
                board[row][col] = i;

                row_filled[row] ^= mask;
                col_filled[col] ^= mask;
                matrix_filled[row / 3][col / 3] ^= mask;

                result = result || fillSudoku_02(index + 1);

                board[row][col] = 0;

                row_filled[row] ^= mask;
                col_filled[col] ^= mask;
                matrix_filled[row / 3][col / 3] ^= mask;
            }
        }

        return result;
    }

    public static void fillPreDefinedValue() {
        for(int i = 0; i < board_row; i++) {
            for( int j = 0; j < board_col; j++) {
                int number = board[i][j];
                if(number != 0) {
                    int twoD_index_x = (i / 3);
                    int twoD_index_y = (j / 3);
                    int fillWith = (1 << number);
                    col_filled[j] = (col_filled[j] | fillWith);
                    row_filled[i] = (row_filled[i] | fillWith);
                    matrix_filled[twoD_index_x][twoD_index_y] = (matrix_filled[twoD_index_x][twoD_index_y] | fillWith);
                }
            }
        }
    }

    public static boolean isValidToPlaceNumber(int r, int c, int number) {
        for(int i = 0; i < board_row; i++) if(board[i][c] == number) return false;

        for(int i = 0; i < board_col; i++) if(board[r][i] == number) return false;

        int x = 3 * (r / 3);
        int y = 3 * (c / 3);
        for(int i = 0; i < 3; i++) 
            for(int j = 0; j < 3; j++)
                if(board[x + i][y + j] == number) return false;

        return true;
    }

    public static boolean fillSudoku_01(int index) {
        if(index == 81) {
            displayMatrix(false);
            return true;
        }

        boolean result = false;

        int row = index / 9;
        int col = index % 9;

        if(board[row][col] == 0) {
            for(int i = 1; i < 10; i++) {
                int mask = (1 << i);
                if((mask & row_filled[row]) == 0 && (mask & col_filled[col]) == 0 && (matrix_filled[row / 3][col / 3] & mask) == 0) {
                    board[row][col] = i;

                    row_filled[row] ^= mask;
                    col_filled[col] ^= mask;
                    matrix_filled[row / 3][col / 3] ^= mask;

                    result = (result || fillSudoku_01(index + 1));

                    board[row][col] = 0;

                    row_filled[row] ^= mask;
                    col_filled[col] ^= mask;
                    matrix_filled[row / 3][col / 3] ^= mask;
                }
            }
        } else 
            fillSudoku_01(index + 1);

        return result;
    }

    public static boolean fillSudoku(int index) {
        if(index == 81) {
            displayMatrix(false);
            return true;
        }

        boolean result = false;
        int x = index / 9;
        int y = index % 9;
        if(board[x][y] == 0) {
            for(int number = 1; number < 10 && !result; number++) {
                if(isValidToPlaceNumber(x, y, number)) {
                    board[x][y] = number;
                    result = (result || fillSudoku(index + 1));
                    board[x][y] = 0;
                }
            }
        } else 
            fillSudoku(index + 1);
        
        return result;
    }

    public static void displayMatrix(boolean original) {
        if(original)
            syso("\nOriginal array : \n");
        else 
            syso("\nFilled array : \n");

        for(int i = 0; i < board_row; i++) {
            if(i % 3 == 0) syso("-------------------------------");
            for(int j = 0; j <= board_col; j++) {
                if(j % 3 == 0) System.out.print("|");
                if(j != board_col)
                    System.out.print(" " + board[i][j] + " ");
            }
            printNewLine();
        }
        syso("-------------------------------");

    }

    public static void displayPreFilledMatrix() {
        for(int i : row_filled) syso(Integer.toBinaryString(i));
        printNewLine();
        for(int i : col_filled) syso(Integer.toBinaryString(i));
        printNewLine();
        for(int i = 0; i < 3; i++) 
            for(int j = 0; j < 3; j++) 
                syso(Integer.toBinaryString(matrix_filled[i][j]));
    }

    public static void cleanState() {
        for(int i = 0; i < row_filled.length; i++) row_filled[i] = 0;
        for(int i = 0; i < col_filled.length; i++) col_filled[i] = 0;
        for(int i = 0; i < 3; i++) 
            for(int j = 0; j < 3; j++) 
                matrix_filled[i][j] = 0;
    }

    // random utility methods
    public static final int FIXED_LEN = 106;
    public static void printLine() {
        syso(String.format("%112s", " ").replaceAll(" ", "*"));
    }

    public static void printLine(String word) {
        printNewLine();
        int write = word.length();
        int fill = (FIXED_LEN - write) / 2;
        syso(String.format("%" + fill + "s", " ").replaceAll(" ", "*") + " : " + word + " : "
                + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "*"));
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void syso(Object obj) {
        System.out.println(obj);
    }

    public static void markHeading(String string) {
        printNewLine();
        printLine();
        syso(string);
        printLine();
        printNewLine();
    }
}