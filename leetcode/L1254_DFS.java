public class L1254_DFS {
  public static void main(String[] args) {
    // int[][] grid =
    // {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
    // int[][] grid = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
    int[][] grid = {
      {1, 1, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 1},
      {1, 0, 1, 1, 1, 0, 1},
      {1, 0, 1, 0, 1, 0, 1},
      {1, 0, 1, 1, 1, 0, 1},
      {1, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1}
    };
    System.out.println(new L1254_DFS().closedIsland(grid));
  }

  int count;

  public int closedIsland(int[][] grid) {

    for (int i = 0; i < grid.length; i++) {
      if (grid[i][0] == 0) {
        callDfs(i, 0, grid);
      }
    }

    for (int i = 0; i < grid.length; i++) {
      int col = grid[0].length - 1;
      if (grid[i][col] == 0) {
        callDfs(i, col, grid);
      }
    }

    for (int i = 0; i < grid[0].length; i++) {
      if (grid[0][i] == 0) {
        callDfs(0, i, grid);
      }
    }

    for (int i = 0; i < grid.length; i++) {
      int row = grid.length - 1;
      if (grid[row][i] == 0) {
        callDfs(row, i, grid);
      }
    }

    for (int i = 1; i < grid.length - 1; i++) {
      for (int j = 1; j < grid[0].length - 1; j++) {
        if (grid[i][j] == 0) {
          callDfs(i, j, grid);
          count++;
        }
      }
    }

    return count;
  }

  int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public void callDfs(int r, int c, int[][] grid) {
    grid[r][c] = 1;
    for (int[] dir : direction) {
      int nx = r + dir[0];
      int ny = c + dir[1];
      if (nx < grid.length && nx >= 0 && ny < grid[0].length && ny >= 0 && grid[nx][ny] == 0)
        callDfs(nx, ny, grid);
    }
  }
}
