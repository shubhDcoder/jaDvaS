import java.util.Arrays;

public class SubStringAndSeq {

  public static int longestPalindromicSubstring(String input) {
    char[] charArr = input.toCharArray();
    int N = charArr.length;
    int[][] dp = new int[N][N];
    for (int stringSize = 0; stringSize < N; stringSize++) {
      for (int row = 0, col = stringSize; col < N; row++, col++) {
        if (row > col) continue;
        else if (stringSize == 0) dp[row][col] = 1;
        else if (stringSize == 1 && charArr[row] == charArr[col]) dp[row][col] = 2;
        else if (charArr[row] == charArr[col] && dp[row + 1][col - 1] > 0) dp[row][col] = stringSize;
      }
    }

    int answer = 0, i = 0, j = 0;
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (answer < dp[row][col]) {
          i = row;
          j = col;
          answer = dp[row][col];
        }
      }
    }
    System.out.println(input.substring(i, j + 1));
    return answer + 1;
  }

  public static boolean[][] isPalindromeSubString(String input) {
    int N = input.length();
    boolean[][] matrix = new boolean[N][N];

    for (int stringSize = 0; stringSize < N; stringSize++) {
      for (int row = 0, col = stringSize; col < N; row++, col++) {
        if (row > col) continue;
        else if (stringSize == 0) matrix[row][col] = true;
        else if (stringSize == 1) matrix[row][col] = input.charAt(row) == input.charAt(col);
        else if (input.charAt(row) == input.charAt(col)) matrix[row][col] = matrix[row + 1][col - 1];
      }
    }

    return matrix;
  }

  public static int longestPalindromicSubseqMem(int s, int e, char[] input, boolean[][] CACHE, int[][] dp) {
    if (CACHE[s][e] == true) dp[s][e] = e - s + 1;
    if (dp[s][e] > 0) return dp[s][e];
    if (input[s] == input[e]) dp[s][e] = longestPalindromicSubseqMem(s + 1, e - 1, input, CACHE, dp) + 2;
    else dp[s][e] = Math.max(longestPalindromicSubseqMem(s, e - 1, input, CACHE, dp), longestPalindromicSubseqMem(s + 1, e, input, CACHE, dp));
    return dp[s][e];
  }

  public static int longestPalindromicSubseqTab(char[] input, boolean[][] CACHE, int[][] dp) {
    int N = input.length;
    for (int stringSize = 0; stringSize < N; stringSize++) {
      for (int row = 0, col = stringSize; col < N; row++, col++) {
        if (CACHE[row][col]) dp[row][col] = col - row + 1;
        else if (stringSize == 2) dp[row][col] = CACHE[row][col] ? 2 : 0;
        else if (input[row] == input[col]) dp[row][col] = dp[row + 1][col - 1] + 2;
        else dp[row][col] = Math.max(dp[row + 1][col], dp[row][col - 1]);
      }
    }

    int answer = 0;
    for (int stringSize = 0; stringSize < N; stringSize++) {
      for (int row = 0, col = stringSize; col < N; row++, col++) {
        if (answer < dp[row][col]) answer = dp[row][col];
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    // System.out.printf("longest substring is %d\n",
    // longestPalindromicSubstring("geeks4skeegabcd"));
    String input = "aklmnnma";
    char[] sendInput = input.toCharArray();
    int N = sendInput.length;
    int[][] dp = new int[N][N];
    boolean[][] CACHE = isPalindromeSubString(input);
    int answer = longestPalindromicSubseqMem(0, N - 1, sendInput, CACHE, dp);
    System.out.printf("longest subseq is %d\n", answer);
    for (int[] arr : dp) System.out.println(Arrays.toString(arr));

    System.out.println("--------------------------------");
    int[][] dp1 = new int[N][N];
    answer = longestPalindromicSubseqTab(sendInput, CACHE, dp1);
    System.out.printf("longest subseq is %d\n", answer);
    for (int[] arr : dp1) System.out.println(Arrays.toString(arr));
  }

  public static void display2D(boolean[][] matrix) {
    for (boolean[] arr : matrix) System.out.println(Arrays.toString(arr));
  }
}
