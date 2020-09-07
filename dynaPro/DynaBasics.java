import java.util.*;

public class DynaBasics {
  /* Fibonacci */
  // recursion
  public static int fibonacci_recursion(int n) {
    return n <= 1 ? n : fibonacci_recursion(n - 1) + fibonacci_recursion(n - 2);
  }

  // memoization
  public static int fibonacci(int number, int[] dp) {
    if (number <= 1) {
      dp[number] = number;
      return dp[number];
    }

    if (dp[number] == 0) dp[number] = fibonacci(number - 1, dp) + fibonacci(number - 2, dp);
    return dp[number];
  }

  // tabulation
  public static int fibonacciDP(int number, int[] dp) {
    for (int i = 0; i <= number; i++) {
      if (i <= 1) {
        dp[i] = i;
        continue;
      }
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[number];
  }

  // better fibonacci
  public static int fibonacci_better(int number) {
    if (number <= 1) return number;
    int a = 0, b = 1;
    for (int i = 2; i <= number; i++) {
      int sum = a + b;
      a = b;
      b = sum;
    }
    return b;
  }

  public static void display(int[] dp) {
    System.out.println(Arrays.toString(dp));
    System.out.println("=======================");
  }

  public static void display2D(int[][] dp) {
    for (int[] arr : dp) System.out.println(Arrays.toString(arr));
    System.out.println("=======================");
  }

  /* // mazepath single jump HVD */
  // Recursive approach
  public static int mazePath_recursion(int sr, int sc, int er, int ec) {
    if (sr == er && sc == ec) return 1;
    int count = 0;
    if (sr + 1 <= er) count += mazePath_recursion(sr + 1, sc, er, ec);
    if (sc + 1 <= ec) count += mazePath_recursion(sr, sc + 1, er, ec);
    if (sc + 1 <= ec && sr + 1 <= er) count += mazePath_recursion(sr + 1, sc + 1, er, ec);
    return count;
  }

  public static int mazePath(int sr, int sc, int er, int ec, int[][] dp) {
    if (sr == er && sc == ec) {
      dp[sr][sc] = 1;
      return dp[sr][sc];
    }
    if (dp[sr][sc] != 0) return dp[sr][sc];
    int count = 0;

    if (sr + 1 <= er) count += mazePath(sr + 1, sc, er, ec, dp);
    if (sc + 1 <= ec) count += mazePath(sr, sc + 1, er, ec, dp);
    if (sc + 1 <= ec && sr + 1 <= er) count += mazePath(sr + 1, sc + 1, er, ec, dp);

    dp[sr][sc] = count;
    return dp[sr][sc];
  }

  public static int mazePathDP(int sr, int sc, int er, int ec, int[][] dp) {
    for (int col = ec; col >= sc; col--) {
      for (int row = er; row >= sr; row--) {
        if (col == ec && row == er) {
          dp[row][col] = 1;
          continue;
        }
        if (col + 1 <= ec) dp[row][col] += dp[row][col + 1];
        if (row + 1 <= er) dp[row][col] += dp[row + 1][col];
        if (col + 1 <= ec && row + 1 <= er) dp[row][col] += dp[row + 1][col + 1];
      }
    }
    return dp[sr][sc];
  }

  /* // mazepath multi jump HVD */
  // recursion
  public static int mazePathWithJump_recursion(int sr, int sc, int er, int ec) {
    if (sr == er && sc == ec) return 1;
    int count = 0;
    for (int j = 1; j <= Math.max(er, ec); j++) {
      if (sr + j <= er) count += mazePathWithJump_recursion(sr + j, sc, er, ec);
      if (sc + j <= ec) count += mazePathWithJump_recursion(sr, sc + j, er, ec);
      if (sr + j <= er && sc + j <= ec) count += mazePathWithJump_recursion(sr + j, sc + j, er, ec);
    }
    return count;
  }

  // memoization
  public static int mazePathWithJump(int sr, int sc, int er, int ec, int[][] dp) {
    if (sr == er && sc == ec) {
      dp[sr][sc] = 1;
      return dp[sr][sc];
    }
    if (dp[sr][sc] != 0) return dp[sr][sc];
    int count = 0;
    for (int i = 1; i <= Math.max(er, ec); i++) {
      if (sc + i <= ec) count += mazePathWithJump(sr, sc + i, er, ec, dp);
      if (sr + i <= er) count += mazePathWithJump(sr + i, sc, er, ec, dp);
      if (sr + i <= er && sc + i <= ec) count += mazePathWithJump(sr + i, sc + i, er, ec, dp);
    }
    dp[sr][sc] = count;
    return dp[sr][sc];
  }

  // tabulation
  public static int mazePathWithJumpDP(int sr, int sc, int er, int ec, int[][] dp) {
    for (int i = er; i >= sr; i--) {
      for (int j = ec; j >= sc; j--) {
        if (i == er && j == ec) {
          dp[i][j] = 1;
          continue;
        }
        for (int d = 1; d <= Math.max(er, ec); d++) {
          if (j + d <= ec) dp[i][j] += dp[i][j + d];
          if (i + d <= er) dp[i][j] += dp[i + d][j];
          if (d + i <= er && d + j <= ec) dp[i][j] += dp[i + d][j + d];
        }
      }
    }
    return dp[sr][sc];
  }

  /* Board Path */
  // Recursion
  public static int boardPath_recursion(int current, int end) {
    if (current == end) return 1;
    int count = 0;
    for (int i = 1; i <= 6; i++)
      if (current + i <= end) count += boardPath_recursion(current + i, end);
      else break;
    return count;
  }

  // Memoization
  public static int boardPath(int current, int end, int[] dp) {
    if (current == end) {
      dp[current] = 1;
      return dp[current];
    }
    if (dp[current] != 0) return dp[current];
    for (int i = 1; i <= 6; i++)
      if (current + i <= end) dp[current] += boardPath(current + i, end, dp);
    return dp[current];
  }

  // Tabulation
  public static int boardPathDP(int current, int end, int[] dp) {
    for (int i = end; i >= current; i--)
      if (i == end) dp[i] = 1;
      else for (int j = 1; j <= 6 && j + i <= end; j++) dp[i] += dp[j + i];

    return dp[current];
  }

  /* Friends pairing problem */
  // recursion
  public static void friendsPairingProblem_rec(String original, String answer) {
    if (original.length() <= 1) {
      System.out.print("[" + answer + original + "] ");
      return;
    }
    friendsPairingProblem_rec(original.substring(1), answer + original.charAt(0) + ",");
    char parent = original.charAt(0);
    original = original.substring(1);
    for (int i = 0; i < original.length(); i++) {
      String newAnswer = answer + parent + original.charAt(i) + ",";
      String newOriginal = original.substring(0, i) + original.substring(i + 1);
      friendsPairingProblem_rec(newOriginal, newAnswer);
    }
  }

  // memoization
  public static int friendsPairingProblem_memo(int number, int[] dp) {
    if (number <= 1) {
      dp[number] = 1;
      return dp[number];
    }

    if (dp[number] != 0) return dp[number];

    int single = friendsPairingProblem_memo(number - 1, dp);
    int pairup = friendsPairingProblem_memo(number - 2, dp) * (number - 1);
    dp[number] = single + pairup;

    return dp[number];
  }

  // Tabulation
  public static int friendsPairingProblem_dp(int number, int[] dp) {
    for (int i = 0; i <= number; i++) {
      if (i <= 1) {
        dp[i] = 1;
        continue;
      }
      dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
    }
    return dp[number];
  }

  public static int friendsPairingProblem_dp_opt(int number) {
    if (number <= 1) return 1;
    int first = 1, second = 1;
    for (int i = 2; i <= number; i++) {
      int third = (i - 1) * first + second;
      first = second;
      second = third;
    }
    return second;
  }

  /* GoldMine problem */
  public static int goldMine_recursion(int[][] input, int sr, int sc, int er, int ec) {
    if (sc == ec) return input[sr][sc];
    int result = Integer.MIN_VALUE;
    if (sr + 1 <= er && sc + 1 <= ec)
      result = Math.max(result, goldMine_recursion(input, sr + 1, sc + 1, er, ec));
    if (sc + 1 <= ec) result = Math.max(result, goldMine_recursion(input, sr, sc + 1, er, ec));
    if (sr - 1 >= 0 && sc + 1 <= ec)
      result = Math.max(result, goldMine_recursion(input, sr - 1, sc + 1, er, ec));
    return result + input[sr][sc];
  }

  public static int goldMine_recursion_pilot(int[][] input) {
    int answer = 0;
    int er = input.length - 1;
    int ec = input[0].length - 1;
    for (int i = 0; i < input.length; i++)
      answer = Math.max(answer, goldMine_recursion(input, i, 0, er, ec));
    return answer;
  }

  public static int goldMine_memo(int[][] input, int sr, int sc, int[][] dp) {
    if (sc == input[0].length - 1) {
      dp[sr][sc] = input[sr][sc];
      return dp[sr][sc];
    }

    if (dp[sr][sc] == 0) {
      int maxCoin = Integer.MIN_VALUE;
      maxCoin = Math.max(maxCoin, goldMine_memo(input, sr, sc + 1, dp));
      if (sr + 1 < input.length)
        maxCoin = Math.max(maxCoin, goldMine_memo(input, sr + 1, sc + 1, dp));
      if (sr - 1 >= 0) maxCoin = Math.max(maxCoin, goldMine_memo(input, sr - 1, sc + 1, dp));
      dp[sr][sc] = maxCoin + input[sr][sc];
    }
    return dp[sr][sc];
  }

  public static int goldMine_memo_pilot(int[][] input) {
    int[][] dp = new int[input.length][input[0].length];
    int maxCoin = 0;
    for (int i = 0; i < input.length; i++)
      maxCoin = Math.max(maxCoin, goldMine_memo(input, i, 0, dp));
    display2D(dp);
    return maxCoin;
  }

  public static int goldMine_tabu(int[][] input) {
    int[][] dp = new int[input.length][input[0].length];
    for (int col = input[0].length - 1; col >= 0; col--) {
      for (int row = input.length - 1; row >= 0; row--) {
        if (col == input[0].length - 1) {
          dp[row][col] = input[row][col];
          continue;
        }
        dp[row][col] = Math.max(dp[row][col], dp[row][col + 1]);
        if (row + 1 < input.length) dp[row][col] = Math.max(dp[row][col], dp[row + 1][col + 1]);
        if (row - 1 >= 0) dp[row][col] = Math.max(dp[row][col], dp[row - 1][col + 1]);
        dp[row][col] += input[row][col];
      }
    }
    int maxCoin = 0;
    for (int row = 0; row < input.length; row++) maxCoin = Math.max(dp[row][0], maxCoin);
    display2D(dp);
    return maxCoin;
  }

  /* partitionIntoKsubsets */
  // Recursion
  public static int partitionIntoKsubsets_rec(int n, int k) {
    if (n == k || k == 1) return 1;
    int count = 0;
    count = partitionIntoKsubsets_rec(n - 1, k - 1) + partitionIntoKsubsets_rec(n - 1, k) * k;
    return count;
  }

  // Memoization
  public static int partitionIntoKsubsets_memo(int n, int k, int dp[][]) {
    if (n == k || k == 1) {
      dp[n][k] = 1;
      return dp[n][k];
    }
    if (dp[n][k] == 0) {
      dp[n][k] = partitionIntoKsubsets_memo(n - 1, k - 1, dp);
      dp[n][k] += partitionIntoKsubsets_memo(n - 1, k, dp) * k;
    }
    return dp[n][k];
  }

  // Tabulation
  public static int partitionIntoKsubsets_tabu(int n, int k, int dp[][]) {
    for (int row = 1; row <= n; row++) {
      for (int col = 1; col <= k; col++) {
        if (row < col) continue;
        if (col == 1 || row == col) {
          dp[row][col] = 1;
          continue;
        }
        dp[row][col] = dp[row - 1][col] * col + dp[row - 1][col - 1];
      }
    }
    return dp[n][k];
  }

  /* Minimum path */
  public static int getMinimumPath(int source, int dest, int[][] input) {
    if (source == dest - 1) return input[source][dest];
    int result = input[source][dest];
    for (int s = source + 1; s < dest; s++)
      result = Math.min(result, getMinimumPath(source, s, input) + getMinimumPath(s, dest, input));
    return result;
  }

  public static int getMinimumPath_memo(int source, int dest, int[][] input, int[][] dp) {
    if (source == dest - 1) {
      dp[source][dest] = input[source][dest];
      return dp[source][dest];
    }
    if (dp[source][dest] == 0) {
      int myCost = input[source][dest];
      int result = Integer.MAX_VALUE;
      for (int s = source + 1; s < dest; s++) {
        int a = getMinimumPath_memo(source, s, input, dp);
        int b = getMinimumPath_memo(s, dest, input, dp);
        result = Math.min(result, a + b);
      }
      dp[source][dest] = Math.min(myCost, result);
    }
    return dp[source][dest];
  }

  public static int getMinimumPath_tabu(int source, int dest, int[][] input) {
    for (int row = input.length - 3; row >= 0; row--) {
      for (int col = row + 2; col < input[0].length; col++) {
        int result = input[row][col];
        for (int k = row + 1; k <= col; k++) {
          result = Math.min(result, input[row][k] + input[k][col]);
        }
        input[row][col] = result;
      }
    }
    return input[source][dest];
  }

  /* SubSequence and Substring problems */

  // bruteforce
  public static void getLongestPalindromicSubstring(String input) {
    int N = input.length();
    char[] array = input.toCharArray();
    int startIndex = 0, endIndex = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        if (array[i] == array[j]) {
          int k = i, l = j;
          boolean isPalindrome = true;
          for (; k < l; k++, l--) {
            if (array[k] != array[l]) {
              isPalindrome = false;
              break;
            }
          }
          if (isPalindrome && endIndex - startIndex < j - i) {
            startIndex = i;
            endIndex = j;
          }
        }
      }
    }
    System.out.println("longest SStrng string is :: " + input.substring(startIndex, endIndex + 1));
  }

  public static int[][] isPalindromicSubString_DP(String input) {
    int N = input.length();
    char[] array = input.toCharArray();
    int dp[][] = new int[N][N];
    for (int gap = 0; gap < N; gap++) dp[gap][gap] = 1;
    for (int gap = 1; gap < N; gap++) dp[gap - 1][gap] = array[gap - 1] == array[gap] ? 2 : 0;
    for (int gap = 2; gap < N; gap++)
      for (int i = 0, j = gap; j < N; i++, j++)
        dp[i][j] = (array[i] == array[j] ? (dp[i + 1][j - 1] > 0 ? 2 + dp[i + 1][j - 1] : 0) : 0);

    // int startIndex = 0, endIndex = 0, maxResult = 0;

    // for (int i = 0; i < N; i++)
    //   for (int j = i + 1; j < N; j++) {
    //     if (dp[i][j] > maxResult) {
    //       endIndex = j;
    //       startIndex = i;
    //       maxResult = dp[i][j];
    //     }
    //   }

    // display2D(dp);
    // System.out.println("longest SStrng string is :: " + input.substring(startIndex, endIndex +
    // 1));
    return dp;
  }

  public static int longestPalindromicSubSeq(String input, int si, int ei, int[][] dp, int[][] pc) {
    if (pc[si][ei] != 0) {
      dp[si][ei] = pc[si][ei];
      return pc[si][ei];
    }
    ;
    if (dp[si][ei] != 0) return dp[si][ei];

    int len = 0;
    if (input.charAt(si) == input.charAt(ei))
      len = longestPalindromicSubSeq(input, si + 1, ei - 1, dp, pc) + 2;
    else {
      int incl = longestPalindromicSubSeq(input, si + 1, ei, dp, pc);
      int excl = longestPalindromicSubSeq(input, si, ei - 1, dp, pc);
      len = Math.max(incl, excl);
    }
    dp[si][ei] = len;
    return len;
  }

  public static int longestPalindromicSubSeqDP(String input, int[][] dp) {
    int N = input.length();
    for (int gap = 0; gap < N; gap++) {
      for (int i = 0, j = gap; j < N; j++, i++) {
        if (gap == 0) dp[i][j] = 1;
        else if (gap == 1) dp[i][j] = input.charAt(i) == input.charAt(j) ? 2 : 1;
        else {
          if (input.charAt(i) == input.charAt(j)) dp[i][j] = 2 + dp[i + 1][j - 1];
          else dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
        }
      }
    }
    return dp[0][N - 1];
  }

  public static void main(String[] args) {
    // forFibonacci();
    // System.out.println("=======================================");
    // forMazePathMultiJump();
    // System.out.println("=======================================");
    // forMazePathSingleJump();
    // System.out.println("=======================================");
    // forBoardPath();
    // System.out.println("=======================================");
    // friendsPairingProblem();
    // System.out.println("=======================================");
    // goldMineProblem();
    // System.out.println("=======================================");
    // partitionIntoKsubsets();
    // System.out.println("=======================================");
    // getMinimumPath();
    forPalindromicSubString();
  }

  /* Driver code */

  public static void forPalindromicSubString() {
    String input1 = "abbkkzadsafkbba";
    // String input1 = "GEEKSFORGEEKS";
    // String input1 = "KMSFUSMD";
    // String input1 = "abbsabba";
    // String input2 = "abbadm";
    // getLongestPalindromicSubstring(input1);
    int[][] pc = isPalindromicSubString_DP(input1);
    int[][] dp = new int[pc.length][pc.length];
    int size = longestPalindromicSubSeq(input1, 0, input1.length() - 1, dp, pc);
    System.out.println("longest palindromic subsequence is " + size);
    // display2D(pc);
    display2D(dp);

    int[][] dp1 = new int[pc.length][pc.length];
    size = longestPalindromicSubSeqDP(input1, dp1);
    System.out.println("longest palindromic subsequence is " + size);
    display2D(dp1);
  }

  public static void forFibonacci() {
    System.out.println("================== Fibonacci ==================");
    int number = 5;
    int dp[] = new int[number + 1];
    System.out.println(fibonacci(number, dp));
    display(dp);
    int dp2[] = new int[number + 1];
    System.out.println(fibonacciDP(number, dp2));
    display(dp2);
    System.out.println("derived from DP : = " + fibonacci_better(5));
    System.out.println("=======================");
  }

  public static void forMazePathSingleJump() {
    System.out.println("================== MazePathHVD ==================");
    int n = 2;
    System.out.println("ways are with Recursion   : " + mazePath_recursion(0, 0, n, n));
    int[][] dp = new int[n + 1][n + 1];
    System.out.println("ways are with Memoization : " + mazePath(0, 0, n, n, dp));
    display2D(dp);
    int[][] dp2 = new int[n + 1][n + 1];
    System.out.println("ways are with Tabulation  : " + mazePathDP(0, 0, n, n, dp2));
    display2D(dp2);
  }

  public static void forMazePathMultiJump() {
    System.out.println("================== MazePathHVD MultiJump  ==================");
    System.out.println("ways are with Recursion   : " + mazePathWithJump_recursion(0, 0, 2, 2));
    int[][] dp = new int[3][3];
    System.out.println("ways are with Memoization : " + mazePathWithJump(0, 0, 2, 2, dp));
    display2D(dp);
    int[][] dp2 = new int[3][3];
    System.out.println("ways are with Tabulation  : " + mazePathWithJumpDP(0, 0, 2, 2, dp2));
    display2D(dp2);
  }

  public static void forBoardPath() {
    System.out.println("================== BoardPath ==================");
    System.out.println("ways are with Recursion   : " + boardPath_recursion(0, 10));
    int[] dp = new int[11];
    System.out.println("ways are with Memoization : " + boardPath(0, 10, dp));
    display(dp);
    int[] dp2 = new int[11];
    System.out.println("ways are with Tabulation  : " + boardPathDP(0, 10, dp2));
    display(dp2);
  }

  public static void friendsPairingProblem() {
    System.out.println("================== FriendCircle ==================");
    friendsPairingProblem_rec("ABCDE", "");
    System.out.println();
    int number = 4;
    int[] dp = new int[number + 1];
    System.out.println("ways are with Memoization : " + friendsPairingProblem_memo(number, dp));
    display(dp);
    int[] dp2 = new int[number + 1];
    System.out.println("ways are with Tabulation  : " + friendsPairingProblem_dp(number, dp2));
    display(dp2);
    System.out.println("ways are with Optimized   : " + friendsPairingProblem_dp_opt(5));
  }

  public static void goldMineProblem() {
    System.out.println("================== GoldMine ==================");
    // int mat[][] = {{1, 3, 1, 5}, {2, 2, 4, 1}, {5, 0, 2, 3}, {0, 6, 1, 2}};
    int mat[][] = {{10, 33, 13, 15}, {22, 21, 04, 1}, {5, 0, 2, 3}, {0, 6, 14, 2}};
    System.out.println("Max coins collected are Recursion   : " + goldMine_recursion_pilot(mat));
    System.out.println("Max coins collected are Memoization : " + goldMine_memo_pilot(mat));
    System.out.println("Max coins collected are tabulation : " + goldMine_tabu(mat));
  }

  public static void partitionIntoKsubsets() {
    int n = 7;
    int k = 3;
    int result = partitionIntoKsubsets_rec(n, k);
    System.out.printf("Recursion   : ways divide %d into %d subsets are %d \n", n, k, result);
    System.out.println("=======================================");
    int[][] dp = new int[n + 1][k + 1];
    result = partitionIntoKsubsets_memo(n, k, dp);
    System.out.printf("Memoization : ways divide %d into %d subsets are %d \n", n, k, result);
    display2D(dp);
    int[][] dp2 = new int[n + 1][k + 1];
    result = partitionIntoKsubsets_tabu(n, k, dp2);
    System.out.printf("Tabulation  : ways divide %d into %d subsets are %d \n", n, k, result);
    display2D(dp2);
  }

  public static void getMinimumPath() {
    // int[][] input = {{0, 10, 75, 95}, {-1, 0, 35, 50}, {-1, -1, 0, 80}, {-1, -1, -1, 0}};
    int[][] input = {
      {10, 5, 15, 45, 77},
      {0, 8, 4, 15, 35},
      {0, 0, 2, 19, 90},
      {0, 0, 0, 15, 70},
      {0, 0, 0, 0, 25},
      {0, 0, 0, 0, 0}
    };
    System.out.println("ways for minimum path is recu : " + getMinimumPath(0, 4, input));
    int[][] dp = new int[input.length][input[0].length];
    System.out.println("ways for minimum path is memo : " + getMinimumPath_memo(0, 4, input, dp));
    display2D(dp);
    System.out.println("ways for minimum path is tabu : " + getMinimumPath_tabu(0, 4, input));
    display2D(input);
  }

  // Links
  // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
}
