public class L0070_DP {
  public int climbStairs_rec(int curr, int n) {
    if (curr == n) return 1;
    int count = 0;
    if (curr + 1 <= n) count += climbStairs_rec(curr + 1, n);
    if (curr + 2 <= n) count += climbStairs_rec(curr + 2, n);
    return count;
  }

  // memoization
  public int climbStairs_memo(int curr, int n, int[] dp) {
    if (n == curr) {
      dp[n] = 1;
      return dp[n];
    }
    if (dp[n] == 0) {
      int count = 0;
      if (curr + 1 <= n) count += climbStairs_memo(curr + 1, n, dp);
      if (curr + 2 <= n) count += climbStairs_memo(curr + 2, n, dp);
      dp[n] = count;
    }
    return dp[n];
  }

  // tabulation
  public int climbStairs(int n) {
    if (n == 0) return 0;
    int[] dp = new int[n + 1];
    dp[n] = 1;
    dp[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) dp[i] = dp[i + 1] + dp[i + 2];
    return dp[0];
  }
}
