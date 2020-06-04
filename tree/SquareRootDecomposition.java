public class SquareRootDecomposition {

  private static int prefixArraySum[];
  private static int[][] queries = {{1, 4}, {2, 5}, {5, 9}, {1, 22}, {18, 22}};
  private static int[] input = {
    1, 2, 3, 4, 5, 6, 7, 3, 2, 1, 7, 9, 53, 1, 44, 3, 12, 71, 2, 14, 51, 32, 41
  };

  public static void main(String[] args) {
    solution1();
    solution2();
  }

  public static void solution2() {
    int elements = (int) Math.ceil(Math.sqrt(input.length));
    int partition[] = new int[elements];
    for (int i = 0; i < input.length; i++) {
      partition[i / elements] += input[i];
    }
    // Arrays.stream(partition).forEach(e -> System.out.print(e + " "));
    int query = 5;
    while (query-- > 0) {
      int left = queries[query][0];
      int right = queries[query][1];
      System.out.printf("from %-2d to %-2d ", left, right);
      int sum = 0;
      while (left % elements != 0) sum += input[left++];
      while (left + elements - 1 <= right) {
        sum += partition[left / elements];
        left += elements;
      }
      while (left <= right) sum += input[left++];
      System.out.print(sum + " \n");
    }
  }

  public static void solution1() {
    prefixArraySum = new int[input.length];
    int sum = 0;
    int index = 0;
    for (int nmbr : input) {
      prefixArraySum[index++] = (sum += nmbr);
    }

    int query = 5;
    while (query-- > 0) {
      int left = queries[query][0];
      int right = queries[query][1];
      System.out.printf("from %-2d to %-2d ", left, right);
      System.out.print(prefixArraySum[right] - (left == 0 ? 0 : prefixArraySum[left - 1]) + "\n");
    }
  }
}
