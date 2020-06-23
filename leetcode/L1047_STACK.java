import java.util.*;

public class L1047_STACK {

  // using stack
  public String removeDuplicates(String S) {
    Deque<Character> stack = new LinkedList<>();
    char[] charArray = S.toCharArray();
    stack.push(charArray[0]);
    for (int i = 1; i < charArray.length; i++) {
      if (!stack.isEmpty() && stack.peek() == charArray[i]) stack.pop();
      else stack.push(charArray[i]);
    }
    StringBuilder builder = new StringBuilder("");
    while (!stack.isEmpty()) builder.insert(0, stack.pop());
    return builder.toString();
  }

  // using string operations
  public String removeDuplicates_V0(String s) {
    int i = 0, j = 0, n = s.length();
    char[] charArray = s.toCharArray();
    for (; j < n; i++, j++) {
      charArray[i] = charArray[j];
      if (i > 0 && charArray[i - 1] == charArray[i]) i -= 2;
    }
    return new String(charArray, 0, i);
  }

  public static void main(String[] args) {
    System.out.println(new L1047_STACK().removeDuplicates_V0("abbbbabbbbaccc"));
  }
}
