import java.util.*;

public class L0746_DP {
  public int minCostClimbingStairs(int[] cost) {
    int N = cost.length;
    int answer[] = new int[N];
    answer[N - 1] = cost[N - 1];
    answer[N - 2] = cost[N - 2];
    for (int i = N - 3; i >= 0; i--) answer[i] = cost[i] + Math.min(answer[i + 1], answer[i + 2]);
    System.out.println(Arrays.toString(answer));
    return Math.min(answer[0], answer[1]);
  }

  public static void main(String[] args) {
    new L0746_DP().minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
    new L0746_DP().minCostClimbingStairsRecursion(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
  }

  public int minCostClimbingStairsRecursion(int[] cost) {
    if (cost.length < 2) return 0;
    if (cost.length == 2) return Integer.min(cost[0], cost[1]);
    int dp[] = new int[cost.length];
    recursion(cost, dp, 0);
    return Integer.min(dp[0], dp[1]);
  }

  public int recursion(int[] cost, int[] dp, int index) {
    if (index >= cost.length - 2) {
      dp[index] = cost[index];
      return dp[index];
    }
    if (dp[index] != 0) return dp[index];

    int onejump = 0, twojump = 0;
    if (index + 1 < cost.length) onejump = recursion(cost, dp, index + 1);
    if (index + 2 < cost.length) twojump = recursion(cost, dp, index + 2);

    dp[index] = cost[index] + Integer.min(onejump, twojump);
    return dp[index];
  }
}
