import java.util.*;

public class L0020_STACK {
  public boolean isValid(String s) {
    if (s.length() == 0) return true;
    if (s.length() == 1) return false;
    Deque<Character> stack = new LinkedList<>();

    for (char c : s.toCharArray()) {
      switch (c) {
        case '{':
        case '(':
        case '[':
          stack.push(c);
          break;
        case '}':
        case ']':
        case ')':
          {
            if (stack.isEmpty()) return false;
            Character popped = stack.pop();
            if ((popped == '(' && c == ')')
                || (popped == '{' && c == '}')
                || (popped == '[' && c == ']')) continue;
            return false;
          }
        default:
          return false;
      }
    }
    return stack.isEmpty();
  }
}
