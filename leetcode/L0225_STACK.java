import java.util.*;

public class L0225_STACK {
  Deque<Integer> queueA;
  Deque<Integer> queueB;
  /** Initialize your data structure here. */
  public L0225_STACK() {
    queueA = new LinkedList<>();
    queueB = new LinkedList<>();
  }

  /** Push element x onto stack. */
  public void push(int x) {
    queueA.addLast(x);
    swap();
  }

  /** Removes the element on top of the stack and returns that element. */
  public int pop() {
    return queueB.pop();
  }

  /** Get the top element. */
  public int top() {
    return queueB.peekFirst();
  }

  /** Returns whether the stack is empty. */
  public boolean empty() {
    return queueB.isEmpty();
  }

  public void swap() {
    while (!queueB.isEmpty()) queueA.addLast(queueB.pop());
    Deque<Integer> queueC = queueA;
    queueA = queueB;
    queueB = queueC;
  }
}
