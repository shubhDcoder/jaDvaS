import java.util.Arrays;

public class L1143_DP {
  public int longestCommonSubsequence(String text1, String text2) {
    // return rec(text1, text2, text1.length(), text2.length());
    int[][] dp = new int[text1.length() + 1][text2.length() + 1];
    int answer = memo(text1, text2, text1.length(), text2.length(), dp);
    for (int[] arr : dp) System.out.println(Arrays.toString(arr));
    int[][] dp1 = new int[text1.length() + 1][text2.length() + 1];
    answer = tabu(text1, text2, dp1);
    System.out.println("---------------------------------------------");
    for (int[] arr : dp1) System.out.println(Arrays.toString(arr));
    return answer;
  }

  public int rec(String s, String t, int si, int tj) {
    if (si == 0 || tj == 0) return 0;
    else {
      if (s.charAt(si - 1) == t.charAt(tj - 1)) return rec(s, t, si - 1, tj - 1) + 1;
      return Math.max(rec(s, t, si - 1, tj), rec(s, t, si, tj - 1));
    }
  }

  public int memo(String s, String t, int si, int tj, int[][] dp) {
    if (si == 0 || tj == 0) return 0;
    else if (dp[si][tj] == 0)
      if (s.charAt(si - 1) == t.charAt(tj - 1)) dp[si][tj] = memo(s, t, si - 1, tj - 1, dp) + 1;
      else {
        dp[si - 1][tj] = memo(s, t, si - 1, tj, dp);
        dp[si][tj - 1] = memo(s, t, si, tj - 1, dp);
        dp[si][tj] = Math.max(dp[si - 1][tj], dp[si][tj - 1]);
      }
    return dp[si][tj];
  }

  public int tabu(String s, String t, int[][] dp) {
    for (int row = 0; row <= s.length(); row++) {
      for (int col = 0; col <= t.length(); col++) {
        if (row == 0 || col == 0) continue;
        if (s.charAt(row - 1) == t.charAt(col - 1)) dp[row][col] = dp[row - 1][col - 1] + 1;
        else dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
      }
    }
    return dp[s.length()][t.length()];
  }

  public static void main(String[] args) {
    String inputA = "abmkmsat";
    String inputB = "almxkzst";
    int answer = new L1143_DP().longestCommonSubsequence(inputA, inputB);
    System.out.println(answer);
  }
}
