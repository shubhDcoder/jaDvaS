import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DynamicProgramming {
  public static int fibonacciRecursion(int number) {
    if (number < 2) return number;
    else return fibonacciRecursion(number - 1) + fibonacciRecursion(number - 2);
  }

  public static int fibonacciMemoization(int number, int[] dp) {
    if (number < 2) {
      dp[number] = number;
      return dp[number];
    }
    if (dp[number] == 0) {
      dp[number] = fibonacciMemoization(number - 1, dp) + fibonacciMemoization(number - 2, dp);
    }
    return dp[number];
  }

  public static int fibonacciTabulation(int number, int[] dp) {
    for (int i = 1; i <= number; i++) {
      if (i < 2) dp[i] = i;
      else dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[number];
  }

  public static int fibonacciOptimized(int number) {
    if (number <= 2) return number;
    int first = 0;
    int second = 1;
    for (int i = 2; i <= number; i++) {
      int third = first + second;
      first = second;
      second = third;
    }
    return second;
  }

  public static int countBoardPathRecursion(int sum, int total) {
    if (sum == total) return 1;

    int count = 0;
    for (int dice = 1; dice < 7; dice++) {
      int newSum = sum + dice;
      if (newSum <= total) count += countBoardPathRecursion(newSum, total);
    }
    return count;
  }

  public static int countBoardPathMemoization(int sum, int total, int dp[]) {
    if (sum == total) {
      dp[total] = 1;
    } else if (dp[sum] == 0) {
      int count = 0;
      for (int dice = 1; dice <= 6; dice++) {
        int newSum = dice + sum;
        if (newSum <= total) count += countBoardPathMemoization(newSum, total, dp);
      }
      dp[sum] = count;
    }
    return dp[sum];
  }

  public static int countBoardPathTabulation(int sum, int total, int dp[]) {
    dp[total] = 1;

    for (int i = total - 1; i >= 0; i--)
      for (int j = 1; j <= 6 && i + j <= total; j++) dp[i] += dp[i + j];
    return dp[0];
  }

  public static int countBoardPathBest(int sum, int total) {
    Deque<Integer> deque = new LinkedList<>();
    deque.addFirst(1);
    deque.addFirst(1);
    for (int i = total - 2; i >= sum; i--) {
      int sumOfSeven = 2 * deque.getFirst();
      if (deque.size() < 7) deque.addFirst(sumOfSeven);
      else deque.addFirst(deque.getFirst() * 2 - deque.removeLast());
    }
    return deque.getFirst();
  }

  public static int mazePathRecursion(int er, int ec, int sr, int sc) {
    if (sr == er && sc == ec) return 1;

    int count = 0;
    if (sr + 1 <= er) count += mazePathRecursion(er, ec, sr + 1, sc);
    if (sc + 1 <= ec) count += mazePathRecursion(er, ec, sr, sc + 1);
    if (sr + 1 <= er && sc + 1 <= ec) count += mazePathRecursion(er, ec, sr + 1, sc + 1);

    return count;
  }

  public static int mazePathMemo(int er, int ec, int sr, int sc, int[][] dp) {
    if (sr == er && sc == ec) {
      dp[sr][sc] = 1;
    } else if (dp[sr][sc] == 0) {
      if (sr + 1 <= er) dp[sr][sc] += mazePathMemo(er, ec, sr + 1, sc, dp);
      if (sc + 1 <= ec) dp[sr][sc] += mazePathMemo(er, ec, sr, sc + 1, dp);
      if (sr + 1 <= er && sc + 1 <= ec) dp[sr][sc] += mazePathMemo(er, ec, sr + 1, sc + 1, dp);
    }
    return dp[sr][sc];
  }

  public static int mazePathTabu(int er, int ec, int sr, int sc, int[][] dp) {
    for (int i = er; i >= 0; i--) {
      for (int j = ec; j >= 0; j--) {
        if (i == er && j == ec) dp[i][j] = 1;
        else {
          if (i + 1 <= er) dp[i][j] += dp[i + 1][j];
          if (j + 1 <= ec) dp[i][j] += dp[i][j + 1];
          if (i + 1 <= er && j + 1 <= ec) dp[i][j] += dp[i + 1][j + 1];
        }
      }
    }
    return dp[sr][sc];
  }

  public static int boardPathRecWithDice(int[] dice, int currentSum, int totalSum) {
    if (currentSum == totalSum) {
      return 1;
    }
    int answer = 0;
    for (int diceNumber : dice) {
      int newCurrentSum = diceNumber + currentSum;
      if (newCurrentSum <= totalSum) answer += boardPathRecWithDice(dice, newCurrentSum, totalSum);
    }
    return answer;
  }

  public static int boardPathMemoWithDice(int[] dice, int currentSum, int totalSum, int dp[]) {
    if (currentSum == totalSum) {
      dp[totalSum] = 1;
      return dp[currentSum];
    }
    if (dp[currentSum] != -1) return dp[currentSum];
    int answer = 0;
    for (int diceNumber : dice) {
      int newCurrentSum = diceNumber + currentSum;
      if (newCurrentSum <= totalSum)
        answer += boardPathMemoWithDice(dice, newCurrentSum, totalSum, dp);
    }
    dp[currentSum] = answer;
    return dp[currentSum];
  }

  public static int boardPathTabuWithDice(int[] dice, int currentSum, int totalSum, int dp[]) {
    dp[totalSum] = 1;
    for (int i = totalSum - 1; i >= 0; i--) {
      for (int diceNumber : dice) {
        int newCurrentSum = diceNumber + i;
        if (newCurrentSum <= totalSum) dp[i] += dp[newCurrentSum];
      }
    }
    return dp[0];
  }

  public static void boardPathWithDiceDriver() {
    int[] dice = {2, 3, 5, 7};
    int total = 10;
    System.out.printf("Rec  : board path dice %d\n", boardPathRecWithDice(dice, 0, total));

    int[] dp = new int[total + 1];
    Arrays.fill(dp, -1);
    System.out.printf("Memo : board path dice %d\n", boardPathMemoWithDice(dice, 0, total, dp));
    display1D(dp);

    int[] dp1 = new int[total + 1];
    System.out.printf("Tabu : board path dice %d\n", boardPathTabuWithDice(dice, 0, total, dp1));
    display1D(dp1);
  }

  public static void boardPathDriver() {
    int total = 30;
    Instant start = Instant.now();
    System.out.printf("Recursion : board path is %d\n", countBoardPathRecursion(0, total));
    Instant end = Instant.now();
    System.out.println("Recursion : " + Duration.between(start, end).toNanos());

    int[] dp = new int[total + 1];
    start = Instant.now();
    System.out.printf("Memoization : board path is %d\n", countBoardPathMemoization(0, total, dp));
    end = Instant.now();
    System.out.println("Tabulation : " + Duration.between(start, end).toNanos());

    dp = new int[total + 1];
    start = Instant.now();
    System.out.printf("Tabulation : board path is %d\n", countBoardPathTabulation(0, total, dp));
    end = Instant.now();
    System.out.println("Tabulation : " + Duration.between(start, end).toNanos());

    start = Instant.now();
    System.out.printf("BestOfAll   : board path is %d\n", countBoardPathBest(0, total));
    end = Instant.now();
    System.out.println("BestOfAll  : " + Duration.between(start, end).toNanos());
  }

  public static void fibonacciDriver() {
    int n = 10;
    System.out.printf("Recursion   : fibonacci of %d is %d\n", n, fibonacciRecursion(n));
    int dp[] = new int[n + 1];
    System.out.printf("Memoization : fibonacci of %d is %d\n", n, fibonacciMemoization(n, dp));
    display1D(dp);
    int dp2[] = new int[n + 1];
    System.out.printf("Tabulation  : fibonacci of %d is %d\n", n, fibonacciTabulation(n, dp2));
    display1D(dp2);
    System.out.printf("Optimized   : fibonacci of %d is %d\n", n, fibonacciOptimized(n));
  }

  public static void mazePathDriver() {
    int er = 2, ec = 2;
    System.out.printf("Recursion : Maze path is %d\n", mazePathRecursion(er, ec, 0, 0));
    int[][] dp = new int[er + 1][ec + 1];
    System.out.printf("Memo      : Maze path is %d\n", mazePathMemo(er, ec, 0, 0, dp));
    int[][] dp1 = new int[er + 1][ec + 1];
    System.out.printf("Tabu      : Maze path is %d\n", mazePathTabu(er, ec, 0, 0, dp1));
  }

  public static void main(String[] args) {
    // fibonacciDriver();
    boardPathDriver();
    // mazePathDriver();
    // boardPathWithDiceDriver();
  }

  public static void display1D(int[] dp) {
    System.out.println(Arrays.toString(dp));
  }
}
