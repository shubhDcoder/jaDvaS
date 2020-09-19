import java.util.Arrays;

public class L0115_DP {
  public int numDistinct(String s, String t) {
    // recursion
    // int result = numDistinct_rec(s, t, 0, 0);
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int[] arr : dp) Arrays.fill(arr, -1);
    int result = numDistinct_memo(s, t, s.length(), t.length(), dp);
    System.out.println(result);
    // for (int[] arr : dp) System.out.println(Arrays.toString(arr));

    System.out.println("-------------------------");

    // tabulation
    int[][] dp1 = new int[s.length() + 1][t.length() + 1];
    result = numDistinct_tabu(s, t, s.length(), t.length(), dp1);
    System.out.println(result);
    // for (int[] arr : dp1) System.out.println(Arrays.toString(arr));

    return result;
  }

  public static int numDistinct_rec(String s, String t, int si, int ti) {
    if (t.length() - ti > s.length() - si) return 0;
    if (ti == t.length()) return 1;

    int answer = 0;
    if (s.charAt(si) == t.charAt(ti)) answer += numDistinct_rec(s, t, si + 1, ti) + numDistinct_rec(s, t, si + 1, ti + 1);
    else answer += numDistinct_rec(s, t, si + 1, ti);
    return answer;
  }

  public static int numDistinct_memo(String s, String t, int sLen, int tLen, int[][] dp) {
    if (tLen == 0) {
      dp[sLen][tLen] = 1;
    } else if (tLen > sLen) {
      dp[sLen][tLen] = 0;
    } else if (dp[sLen][tLen] == -1) {
      int answer = numDistinct_memo(s, t, sLen - 1, tLen, dp);
      if (s.charAt(sLen - 1) == t.charAt(tLen - 1)) answer += numDistinct_memo(s, t, sLen - 1, tLen - 1, dp);
      dp[sLen][tLen] = answer;
    }
    return dp[sLen][tLen] == -1 ? 0 : dp[sLen][tLen];
  }

  public static int numDistinct_tabu(String s, String t, int sLen, int tLen, int[][] dp) {
    for (int row = 0; row <= sLen; row++) {
      for (int col = 0; col <= tLen; col++) {
        if (col == 0) dp[row][col] = 1;
        else if (row < col) continue;
        else if (s.charAt(row - 1) == t.charAt(col - 1)) dp[row][col] = dp[row - 1][col] + dp[row - 1][col - 1];
        else dp[row][col] = dp[row - 1][col];
      }
    }
    return dp[sLen][tLen];
  }

  public static void main(String[] args) {
    // new L0115_DP().numDistinct("babgbag", "bag");

    String input =
        "xslledayhxhadmctrliaxqpokyezcfhzaskeykchkmhpyjipxtsuljkwkovmvelvwxzwieeuqnjozrfwmzsylcwvsthnxujvrkszqwtglewkycikdaiocglwzukwovsghkhyidevhbgffoqkpabthmqihcfxxzdejletqjoxmwftlxfcxgxgvpperwbqvhxgsbbkmphyomtbjzdjhcrcsggleiczpbfjcgtpycpmrjnckslrwduqlccqmgrdhxolfjafmsrfdghnatexyanldrdpxvvgujsztuffoymrfteholgonuaqndinadtumnuhkboyzaqguwqijwxxszngextfcozpetyownmyneehdwqmtpjloztswmzzdzqhuoxrblppqvyvsqhnhryvqsqogpnlqfulurexdtovqpqkfxxnqykgscxaskmksivoazlducanrqxynxlgvwonalpsyddqmaemcrrwvrjmjjnygyebwtqxehrclwsxzylbqexnxjcgspeynlbmetlkacnnbhmaizbadynajpibepbuacggxrqavfnwpcwxbzxfymhjcslghmajrirqzjqxpgtgisfjreqrqabssobbadmtmdknmakdigjqyqcruujlwmfoagrckdwyiglviyyrekjealvvigiesnvuumxgsveadrxlpwetioxibtdjblowblqvzpbrmhupyrdophjxvhgzclidzybajuxllacyhyphssvhcffxonysahvzhzbttyeeyiefhunbokiqrpqfcoxdxvefugapeevdoakxwzykmhbdytjbhigffkmbqmqxsoaiomgmmgwapzdosorcxxhejvgajyzdmzlcntqbapbpofdjtulstuzdrffafedufqwsknumcxbschdybosxkrabyfdejgyozwillcxpcaiehlelczioskqtptzaczobvyojdlyflilvwqgyrqmjaeepydrcchfyftjighntqzoo";
    String search = "rwmimatmhydhbujebqehjprrwfkoebcxxqfktayaaeheys";

    // String input = "rabbbit";
    // String search = "rabbit";

    new L0115_DP().numDistinct(input, search);
  }
}
