public class L0064_DP {
  public int minPathSum(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int er = grid.length;
    int ec = grid[0].length;
    return minPathSum_rec(grid, 0, 0, er, ec);
  }

  public int minPathSum_rec(int[][] grid, int sr, int sc, int er, int ec) {
    if (sr == er && sc == ec) return grid[sr][sc];
    int right = 0, bottom = 0;
    if (sc + 1 <= ec) right = minPathSum_rec(grid, sr, sc + 1, er, ec);
    if (sr + 1 <= er) bottom = minPathSum_rec(grid, sr + 1, sc, er, ec);
    if (right == 0 || bottom == 0) return grid[sr][sc] + (right == 0 ? bottom : right);
    return Math.min(right, bottom) + grid[sr][sc];
  }

  public int minPathSum_memo(int[][] grid, int sr, int sc, int er, int ec, int[][] dp) {
    if (sr == er && sc == ec) {
      dp[sr][sc] = grid[sr][sc];
      return dp[sr][sc];
    }
    if (dp[sr][sc] == 0) {
      int right = Integer.MAX_VALUE, bottom = Integer.MAX_VALUE;
      if (sc + 1 <= ec) right = minPathSum_rec(grid, sr, sc + 1, er, ec);
      if (sr + 1 <= er) bottom = minPathSum_rec(grid, sr + 1, sc, er, ec);
      dp[sr][sc] = Math.min(right, bottom) + grid[sr][sc];
    }

    return dp[sr][sc];
  }

  public int minPathSum_tabu(int[][] grid, int sr, int sc, int er, int ec, int[][] dp) {
    for (int i = ec; i >= 0; i--) {
      for (int j = er; j >= 0; j--) {
        if (i == ec && j == er) {
          dp[j][i] = grid[j][i];
          continue;
        }
        int right = Integer.MAX_VALUE, bottom = Integer.MAX_VALUE;
        if (i + 1 <= ec) right = dp[j][i + 1];
        if (j + 1 <= er) bottom = dp[j + 1][i];
        dp[j][i] = Math.min(right, bottom) + grid[j][i];
      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {}
}
