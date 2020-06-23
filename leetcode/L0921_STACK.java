import java.util.*;

public class L0921_STACK {
  public int minAddToMakeValid_v0(String S) {
    if (S.length() == 0) return 0;
    Deque<Character> stack = new LinkedList<>();
    char[] input = S.toCharArray();
    stack.push(input[0]);
    for (int i = 1; i < input.length; i++) {
      if (!stack.isEmpty() && stack.peek() == '(' && input[i] == ')') stack.pop();
      else stack.push(input[i]);
    }
    return stack.size();
  }

  public int minAddToMakeValid(String S) {
    if (S.length() == 0) return 0;
    int opening = 0, closing = 0;
    char[] input = S.toCharArray();
    for (char c : input) {
      if (c == '(') opening++;
      else if (opening > 0) opening--;
      else closing++;
    }
    return opening + closing;
  }

  public static void main(String[] args) {
    System.out.println(new L0921_STACK().minAddToMakeValid("))()(("));
  }
}
