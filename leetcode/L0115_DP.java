import java.util.Arrays;

public class L0115_DP {
  public int numDistinct(String s, String t) {
    // int result = numDistinct_rec(s, t, 0, 0);
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    int result = numDistinct_memo(s, t, 0, 0, dp);
    System.out.println(result);
    for (int[] arr : dp) System.out.println(Arrays.toString(arr));
    return result;
  }

  public static int numDistinct_rec(String s, String t, int si, int ti) {
    if (t.length() - ti > s.length() - si) return 0;
    if (ti == t.length()) return 1;

    int answer = 0;
    if (s.charAt(si) == t.charAt(ti))
      answer += numDistinct_rec(s, t, si + 1, ti) + numDistinct_rec(s, t, si + 1, ti + 1);
    else answer += numDistinct_rec(s, t, si + 1, ti);
    return answer;
  }

  public static int numDistinct_memo(String s, String t, int si, int ti, int[][] dp) {
    if (t.length() - ti > s.length() - si) {
      dp[si][ti] = -1;
      return 0;
    }
    ;
    if (ti == t.length()) {
      dp[si][ti] = 1;
      return 1;
    }

    if (dp[si][ti] != 0) return dp[si][ti] == -1 ? 0 : dp[si][ti];
    int answer = 0;
    if (s.charAt(si) == t.charAt(ti))
      answer += numDistinct_memo(s, t, si + 1, ti, dp) + numDistinct_memo(s, t, si + 1, ti + 1, dp);
    else answer += numDistinct_memo(s, t, si + 1, ti, dp);

    dp[si][ti] = answer;
    return answer;
  }

  public static void main(String[] args) {
    new L0115_DP().numDistinct("babgbag", "bag");
    new L0115_DP().numDistinct("rabbbit", "rabbit");
  }
}
