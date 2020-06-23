import java.util.*;

public class L0946_STACK {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    if (pushed.length != popped.length) return false;

    Deque<Integer> spushed = new ArrayDeque<>();
    int popIndex = 0;

    for (int p : pushed) {
      spushed.push(p);
      while (!spushed.isEmpty() && spushed.peek() == popped[popIndex]) {
        spushed.pop();
        popIndex++;
      }
    }

    return spushed.isEmpty();
    // if (spushed.size() != spopped.size()) return false;
    // while (!spushed.isEmpty() && !spopped.isEmpty()) {
    //   if (spushed.pop() != spopped.pop()) return false;
    // }
  }
}
