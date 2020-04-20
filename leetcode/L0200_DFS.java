public class L0200_DFS {

    public static char[][] grid = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};

    public static void main(String args[]) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    giveMeAllConnectedIslands(i, j, grid);
                }
            }
        }
        System.out.println(count);
    }

    public static int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void giveMeAllConnectedIslands(int row, int column, char[][] grid) {
        grid[row][column] = '0';

        for(int i = 0; i < directions.length; i++) {
            int nrow = row + directions[i][0];
            int ncol = column + directions[i][1];
            if(isValid(nrow, ncol, grid)) {
                giveMeAllConnectedIslands(nrow, ncol, grid);
            }
        }
    }

    public static boolean isValid(int row, int column, char[][] grid) {
        if(row < 0 || column < 0 || row > grid.length - 1 || column > grid[0].length - 1 || grid[row][column] == '0') return false;
        return true;
    }
}