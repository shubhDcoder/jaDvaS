import java.util.*;

public class OneRecursion {
  public static int findFactorial(int number) {
    return number < 2 ? number : number * findFactorial(number - 1);
  }

  public static void prefixSum(int[] arr, int curr, int[] ans) {
    if (curr == arr.length) return;
    if (curr == 0) ans[curr] = arr[curr];
    else ans[curr] = arr[curr] + ans[curr - 1];
    prefixSum(arr, curr + 1, ans);
  }

  public static void towerOfHanoi(int plates, char S, char V, char D) {
    if (plates <= 0) return;
    towerOfHanoi(plates - 1, S, D, V);
    System.out.println("placing plate " + plates + " from " + S + " to " + D);
    towerOfHanoi(plates - 1, V, S, D);
  }

  public static void main(String[] args) {
    System.out.println(findFactorial(5));
    int[] sum = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    int[] ans = new int[sum.length];
    prefixSum(sum, 0, ans);
    System.out.println(Arrays.toString(ans));
    towerOfHanoi(4, 'S', 'E', 'D');
  }
}
