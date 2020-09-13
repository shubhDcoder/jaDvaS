import java.util.Arrays;

public class SetIntoKSubset {
  public static void main(String[] args) {
    int total = 5, left = 3;
    System.out.println(recursion(left, total));
    int[][] dp = new int[left][total];
    System.out.println(memoization(left, total, dp));
    for (int[] arr : dp) System.out.println(Arrays.toString(arr));

    int[][] dp1 = new int[total][left];
    System.out.println(tabulation(left, total, dp1));
    for (int[] arr : dp1) System.out.println(Arrays.toString(arr));
  }

  public static int recursion(int left, int total) {
    if (left == total || left == 1) return 1;
    int answer = 0;
    answer += recursion(left - 1, total - 1);
    answer += (left * recursion(left, total - 1));
    return answer;
  }

  public static int memoization(int left, int total, int[][] dp) {
    int r = left - 1, c = total - 1;
    if (left == 1 || left == total) dp[r][c] = 1;
    else if (dp[r][c] == 0) {
      dp[r][c] += memoization(left - 1, total - 1, dp);
      dp[r][c] += (left * memoization(left, total - 1, dp));
    }
    return dp[r][c];
  }

  public static int tabulation(int left, int total, int[][] dp) {
    for (int personLeft = 1; personLeft <= total; personLeft++) {
      for (int group = 1; group <= left; group++) {
        int row = personLeft - 1, col = group - 1;
        if (group == 1 || personLeft == group) dp[row][col] = 1;
        else if (personLeft < group) continue;
        else dp[row][col] = dp[row - 1][col - 1] + (group) * dp[row - 1][col];
      }
    }
    return dp[total - 1][left - 1];
  }
}
