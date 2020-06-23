import java.util.*;

public class L0739_STACK {
  public int[] dailyTemperatures(int[] T) {
    int n = T.length;
    if (n == 0) return new int[0];
    int[] answer = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(0);
    for (int i = 1; i < n; i++) {
      while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
        int index = stack.pop();
        answer[index] = i - index;
      }
      stack.push(i);
    }
    return answer;
  }
}
