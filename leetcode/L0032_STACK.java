import java.util.*;

public class L0032_STACK {
  public int longestValidParentheses_v0(String s) {
    Deque<Integer> stack = new LinkedList<>();
    char[] input = s.toCharArray();

    for (int i = 0; i < input.length; i++) {
      if (input[i] == ')' && !stack.isEmpty() && input[stack.peekFirst()] == '(') {
        input[i] = '*';
        input[stack.removeFirst()] = '*';
        continue;
      }
      stack.addFirst(i);
    }

    int answer = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] == '*') {
        int j = i;
        while (j < input.length && input[j] == '*') j++;
        answer = Math.max(answer, j - i);
        i = j;
      }
    }
    return answer;
  }

  public int longestValidParentheses_v1(String s) {
    Deque<Integer> stack = new LinkedList<>();
    stack.addFirst(-1);
    char[] input = s.toCharArray();
    int answer = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] == ')' && stack.peekFirst() != -1 && input[stack.peekFirst()] == '(') {
        stack.pop();
        answer = Math.max(answer, i - stack.peekFirst());
      } else stack.addFirst(i);
    }
    return answer;
  }

  public int longestValidParentheses(String s) {
    Deque<Integer> stack = new LinkedList<>();
    stack.addFirst(-1);
    char[] input = s.toCharArray();
    int answer = 0;
    for (int i = 0; i < input.length; i++)
      if (input[i] == ')' && stack.peekFirst() != -1 && input[stack.peekFirst()] == '(')
        stack.removeFirst();
      else stack.addFirst(i);

    stack.addFirst(input.length);

    while (!stack.isEmpty()) {
      int poppedIndex = stack.pop();
      if (!stack.isEmpty()) answer = Math.max(answer, poppedIndex - stack.peekFirst() - 1);
    }
    return answer;
  }

  public static void main(String[] args) {
    System.out.println(new L0032_STACK().longestValidParentheses(")()())"));
  }
}
