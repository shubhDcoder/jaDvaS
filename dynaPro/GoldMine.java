import java.util.Arrays;
import java.util.Scanner;

public class GoldMine {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextLine()) {
      int totalTestCase = Integer.parseInt(scanner.nextLine());
      while (totalTestCase-- > 0) {
        String[] dimension = scanner.nextLine().split(" ");
        String[] matrixAsString = scanner.nextLine().split(" ");
        int row = Integer.parseInt(dimension[0]);
        int col = Integer.parseInt(dimension[1]);
        int matrix[][] = new int[row][col];
        for (int i = 0; i < matrixAsString.length; i++)
          matrix[i / row][i % col] = Integer.parseInt(matrixAsString[i]);
        int answer = 0;
        int dp[][] = new int[row][col];
        for (int[] arr : dp) Arrays.fill(arr, -1);
        // for (int i = 0; i < row; i++) {
        // answer = Math.max(answer, recursion(matrix, i, 0, row - 1, col - 1));
        // answer = Math.max(answer, memoization(matrix, i, 0, row - 1, col - 1, dp));
        // }
        tabulation(matrix, row - 1, col - 1, dp);
        for (int[] arr : dp) answer = Math.max(answer, arr[0]);
        System.out.println(answer);
      }
    }
    scanner.close();
  }

  // tabulation
  public static void tabulation(int[][] grid, int er, int ec, int[][] dp) {
    for (int col = ec; col >= 0; col--) {
      for (int row = er; row >= 0; row--) {
        if (col == ec) dp[row][col] = grid[row][col];
        else {
          int vUp = 0, vDown = 0;
          if (row - 1 >= 0) vUp = dp[row - 1][col + 1];
          if (row + 1 <= er) vDown = dp[row + 1][col + 1];
          dp[row][col] = Math.max(Math.max(vUp, vDown), dp[row][col + 1]) + grid[row][col];
        }
      }
    }
  }

  // memoization
  public static int memoization(int[][] grid, int r, int c, int er, int ec, int[][] dp) {
    if (c == ec) {
      dp[r][c] = grid[r][c];
    } else if (dp[r][c] == -1) {
      int vUp = 0, vDown = 0, right = 0;
      if (r - 1 >= 0 && c + 1 <= ec) vUp = memoization(grid, r - 1, c + 1, er, ec, dp);
      if (c + 1 <= ec) right = memoization(grid, r, c + 1, er, ec, dp);
      if (r + 1 <= er && c + 1 <= ec) vDown = memoization(grid, r + 1, c + 1, er, ec, dp);
      dp[r][c] = Math.max(Math.max(vUp, vDown), right) + grid[r][c];
    }
    return dp[r][c];
  }

  // recursion
  public static int recursion(int[][] grid, int r, int c, int er, int ec) {
    if (c == ec) return grid[r][c];

    int vUp = 0, vDown = 0, right = 0;
    if (r - 1 >= 0 && c + 1 <= ec) vUp = recursion(grid, r - 1, c + 1, er, ec);
    if (c + 1 <= ec) right = recursion(grid, r, c + 1, er, ec);
    if (r + 1 <= er && c + 1 <= ec) vDown = recursion(grid, r + 1, c + 1, er, ec);

    return Math.max(Math.max(vUp, vDown), right) + grid[r][c];
  }
}
