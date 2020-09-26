import java.util.Scanner;

public class LongestComSubStr {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextLine()) {
      int totalTC = Integer.parseInt(scanner.nextLine());
      while (totalTC-- > 0) {
        String[] input = scanner.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        String s = scanner.nextLine();
        String t = scanner.nextLine();
        int[][] dp = new int[N][M];

        for (int i = N - 1; i >= 0; i--) {
          for (int j = M - 1; j >= 0; j--) {
            if (i == N - 1 || j == M - 1) dp[i][j] = s.charAt(i) == t.charAt(j) ? 1 : 0;
            else dp[i][j] = s.charAt(i) == t.charAt(j) ? dp[i + 1][j + 1] + 1 : 0;
          }
        }

        int answer = 0;
        for (int row = 0; row < N; row++) for (int col = 0; col < M; col++) if (dp[row][col] > answer) answer = dp[row][col];
        System.out.println(answer);
      }
    }
    scanner.close();
  }
}
