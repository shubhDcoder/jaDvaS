import java.util.*;

public class L1249_STACK {
  public String minRemoveToMakeValid(String s) {
    int N = s.length();
    if (N == 0) return "";
    Deque<Integer> stack = new LinkedList<>();
    char[] array = s.toCharArray();

    for (int i = 0; i < N; i++) {
      if (array[i] == '(') stack.push(i);
      else if (array[i] == ')' && !stack.isEmpty() && array[stack.peek()] == '(') stack.pop();
      else if (array[i] == ')') stack.push(i);
    }

    while (!stack.isEmpty()) array[stack.pop()] = '*';

    StringBuilder builder = new StringBuilder();
    for (char c : array) if (c != '*') builder.append(c);
    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(new L1249_STACK().minRemoveToMakeValid("()"));
  }
}
