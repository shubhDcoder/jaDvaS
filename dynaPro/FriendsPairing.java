import java.time.Duration;
import java.time.Instant;

public class FriendsPairing {

  // no need for group, just for understanding
  public static long friendsPairingProblemRec(int group, int left) {
    if (left <= 1) return 1;
    long count = 0;
    count += friendsPairingProblemRec(group + 1, left - 1);
    count += (left - 1) * friendsPairingProblemRec(group + 2, left - 2);
    return count;
  }

  public static long friendsPairingProblemMemo(int group, int left, long[] dp) {
    if (left <= 1) dp[left] = 1;
    else if (dp[left] != 0) return dp[left];
    else {
      dp[left] += friendsPairingProblemMemo(group + 1, left - 1, dp);
      dp[left] += friendsPairingProblemMemo(group + 2, left - 2, dp) * (left - 1);
    }
    return dp[left];
  }

  public static long friendsPairingProblemTabu(int group, int left, long[] dp) {
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= left; i++) dp[i] = dp[i - 1] + (dp[i - 2] * (i - 1));
    return dp[left];
  }

  public static long friendsPairingProblemBest(int group, int left) {
    long minus1 = 1;
    long minus2 = 1;
    long answer = 1;
    for (int i = 2; i <= left; i++) {
      answer = (minus1) + (minus2) * (i - 1);
      minus2 = minus1;
      minus1 = answer;
    }
    return answer;
  }

  public static void friendsPairingProblemDriver() {
    int total = 84;
    Instant start = Instant.now();
    // long answer = friendsPairingProblemRec(0, total);
    Instant end = Instant.now();
    // System.out.printf("rec ans - %d in %d Nsec\n", answer, Duration.between(start,
    // end).toNanos());

    long dp[] = new long[total + 1];
    start = Instant.now();
    long answer = friendsPairingProblemMemo(0, total, dp);
    end = Instant.now();
    System.out.printf("Mem ans - %d in %d Nsec\n", answer, Duration.between(start, end).toNanos());

    // dp = new long[total + 1];
    // start = Instant.now();
    // answer = friendsPairingProblemTabu(0, total, dp);
    // end = Instant.now();
    // System.out.printf("Tabu ans - %d in %d Nsec\n", answer, Duration.between(start,
    // end).toNanos());
    // System.out.println(Arrays.toString(dp));

    // start = Instant.now();
    // answer = friendsPairingProblemBest(0, total);
    // end = Instant.now();
    // System.out.printf("Best ans - %d in %d Nsec\n", answer, Duration.between(start,
    // end).toNanos());
  }

  public static void main(String args[]) {

    friendsPairingProblemDriver();
  }
}
