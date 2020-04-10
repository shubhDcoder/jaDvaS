public class RatInMaze {

    public static int[][] board = {
                                    {1, 0, 0, 0},
                                    {1, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 1, 1}};

    public static void main(String args[]) {
        System.out.println("\ncan rat find path : " + helpPoorRat(0, 0, 3, 3, ""));
    }

    public static boolean helpPoorRat(int sr, int sc, int er, int ec, String answer) {
        // System.out.println("stack - > " + sr + ":" + sc);
        // printMatrix(board);

        if(sr == er && sc == ec) {
            System.out.println(answer + ", ");
            return true;
        }
        board[sr][sc] = 5;
        boolean result = false;
        // horizontal call
        if(sc < ec && board[sr][sc + 1] != 0) {
            result = helpPoorRat(sr, sc + 1, er, ec, answer + "H");
        }
        // vertical call
        if(sr < er && board[sr + 1][sc] != 0 && !result) {
            result = helpPoorRat(sr + 1, sc, er, ec, answer + "V");
        }
        board[sr][sc] = 1;
        // System.out.println("stack - > " + sr + ":" + sc);
        // printMatrix(board);
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%-3d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}