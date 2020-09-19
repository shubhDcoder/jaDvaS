import java.util.Arrays;

public class L0730_DP {
  public int countPalindromicSubsequences(String S) {
    char[] input = S.toCharArray();
    int N = input.length;
    int dp[][] = new int[N][N];

    for (int stringSize = 0; stringSize < N; stringSize++) {
      for (int row = 0, col = stringSize; col < N; row++, col++) {
        if (stringSize <= 1) dp[row][col] = stringSize + 1;
        else {
          int sum = dp[row][col - 1] + dp[row + 1][col];
          dp[row][col] = (input[row] == input[col]) ? sum : sum - dp[row + 1][col - 1];
        }
      }
    }

    for (int[] arr : dp) System.out.println(Arrays.toString(arr));

    return (int) (dp[0][N - 1] % (1e7 + 7));
  }

  public static void main(String[] args) {
    new L0730_DP().countPalindromicSubsequences("bccb");
  }
}
