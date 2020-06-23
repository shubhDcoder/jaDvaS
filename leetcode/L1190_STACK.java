import java.util.*;

public class L1190_STACK {
  public String reverseParentheses(String s) {
    Deque<StringBuilder> stack = new LinkedList<>();
    stack.push(new StringBuilder());
    for (char c : s.toCharArray()) {
      if (c == '(') stack.push(new StringBuilder());
      else if (c == ')') {
        String toBeAdded = stack.pop().reverse().toString();
        stack.peek().append(toBeAdded);
      } else stack.peek().append(c);
    }
    return stack.peek().toString();
  }

  public static void main(String[] args) {
    System.out.println(new L1190_STACK().reverseParentheses("(abcd)"));
  }
}
