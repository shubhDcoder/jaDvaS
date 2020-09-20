import java.time.Duration;
import java.time.Instant;

public class CountPalSubSeq {

  public static int countPalSubSeq_rec(int si, int ei, String input) {
    if (si == ei) return 1;
    if (si > ei) return 0;

    int answer = countPalSubSeq_rec(si, ei - 1, input) + countPalSubSeq_rec(si + 1, ei, input);
    if (input.charAt(si) == input.charAt(ei)) return answer + 1;
    else return answer - countPalSubSeq_rec(si + 1, ei - 1, input);
  }

  public static int countPalSubSeq_memo(int si, int ei, String input, int dp[][]) {
    if (si == ei) {
      dp[si][ei] = 1;
      return 1;
    } else if (si > ei) {
      return 0;
    } else if (dp[si][ei] == 0) {
      int excludingLast = countPalSubSeq_memo(si, ei - 1, input, dp);
      dp[si][ei - 1] = excludingLast;
      int excludingFirst = countPalSubSeq_memo(si + 1, ei, input, dp);
      dp[si + 1][ei] = excludingFirst;
      int answer = excludingFirst + excludingLast;
      if (input.charAt(si) == input.charAt(ei)) {
        dp[si][ei] = answer + 1;
      } else {
        int excludeMiddle = countPalSubSeq_memo(si + 1, ei - 1, input, dp);
        dp[si + 1][ei - 1] = excludeMiddle;
        dp[si][ei] = answer - excludeMiddle;
      }
    }
    return dp[si][ei];
  }

  public static void main(String[] args) {
    String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
    int N = input.length();
    Instant start = Instant.now();
    int answer = countPalSubSeq_rec(0, N - 1, input);
    Instant end = Instant.now();
    System.out.printf("memo - %d in %d\n", answer, Duration.between(start, end).toNanos());

    int dp[][] = new int[N][N];
    start = Instant.now();
    answer = countPalSubSeq_memo(0, N - 1, input, dp);
    end = Instant.now();
    System.out.printf("tabu - %d in %d\n", answer, Duration.between(start, end).toNanos());
  }
}
