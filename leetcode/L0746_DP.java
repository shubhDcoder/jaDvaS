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
  }
}
