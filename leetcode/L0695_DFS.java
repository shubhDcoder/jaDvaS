public class L0695_DFS {
    public static void main(String args[]) {
        // int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
        //                 {0,0,0,0,0,0,0,1,1,1,0,0,0},
        //                 {0,1,1,0,1,0,0,0,0,0,0,0,0},
        //                 {0,1,0,0,1,1,0,0,1,0,1,0,0},
        //                 {0,1,0,0,1,1,0,0,1,1,1,0,0},
        //                 {0,0,0,0,0,0,0,0,0,0,1,0,0},
        //                 {0,0,0,0,0,0,0,1,1,1,0,0,0},
        //                 {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        int[][] grid = {{1, 1}};

        System.out.println("max area is : " + new L0695_DFS().maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0) return 0;

        int maxCount = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    maxCount = Math.max(callDFS(i, j, grid) + 1, maxCount);
                }
            }
        }

        return maxCount;
    }

    public static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int callDFS(int row, int col, int[][] grid) {
        int count = 0;
        grid[row][col] = 0;
        for(int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];   
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) 
                count = count + callDFS(r, c, grid) + 1;
        }
        return count;
    }
}