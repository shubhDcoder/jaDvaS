import java.util.*;

public class L0155_STACK {
  Deque<Long> stack;
  long currMinimum = 0;
  /** initialize your data structure here. */
  public L0155_STACK() {
    stack = new LinkedList<>();
  }

  public void push(long x) {
    if (stack.isEmpty()) currMinimum = x;
    else if (currMinimum > x) {
      stack.push((2l * (long) (x) - currMinimum));
      currMinimum = x;
      return;
    }
    stack.push((long) x);
  }

  public void pop() {
    if (stack.peekFirst() < currMinimum) {
      currMinimum = (2l * (currMinimum) - stack.peekFirst());
    }
    stack.removeFirst();
  }

  public int top() {
    if (stack.peekFirst() < currMinimum) {
      return (int) (2 * (currMinimum) - stack.peekFirst());
    }
    return stack.peekFirst().intValue();
  }

  public int getMin() {
    return (int) currMinimum;
  }
}
