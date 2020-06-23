public class L1209_STACK {
  public String removeDuplicates(String s, int k) {
    if (s.length() < k) return s;

    int[] count = new int[s.length()];
    char[] input = s.toCharArray();
    int i = 0, j = 0;
    for (; j < s.length(); j++, i++) {
      input[i] = input[j];
      if (i > 0) {
        if (input[i] == input[i - 1]) count[i] = count[i - 1] + 1;
        else count[i] = 1;
      } else count[i] = 1;
      if (count[i] == k) i -= k;
    }
    return new String(input, 0, i);
  }

  public static void main(String[] args) {
    System.out.println(new L1209_STACK().removeDuplicates("deeedbbcccbdaa", 3));
  }
}
