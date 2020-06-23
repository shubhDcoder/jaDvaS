import java.util.*;

public class L0856_STACK {
  public int scoreOfParentheses(String S) {
    Deque<Integer> stack = new LinkedList<>();
    int score = 0;
    for (char c : S.toCharArray()) {
      if (c == '(') {
        stack.push(score);
        score = 0;
      } else {
        score = stack.pop() + Math.max(2 * score, 1);
      }
    }
    return score;
  }
}
