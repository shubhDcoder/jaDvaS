import java.util.*;

public class L0402_STACK {
  public String removeKdigits(String num, int k) {
    if (k == 0) return num;
    if (num.length() == 0 || num.length() == k) return "0";

    Deque<Character> deque = new LinkedList<>();
    char[] input = num.toCharArray();
    deque.push(input[0]);

    for (int index = 1; index < num.length(); index++) {
      while (!deque.isEmpty() && k > 0 && deque.peek() > input[index]) {
        deque.pop();
        k--;
      }
      deque.push(input[index]);
    }

    while (!deque.isEmpty() && deque.peekLast() == '0') deque.removeLast();

    char[] answer = new char[deque.size()];
    for (int i = answer.length - 1; i >= 0; i--) answer[i] = deque.pop();
    return new String(answer);
  }

  public static void main(String[] args) {
    System.out.println(new L0402_STACK().removeKdigits("10200", 1));
  }
}
