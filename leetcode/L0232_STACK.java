import java.util.Deque;
import java.util.LinkedList;

public class L0232_STACK {

  Deque<Integer> stackA;
  Deque<Integer> stackB;

  /** Initialize your data structure here. */
  public L0232_STACK() {
    stackA = new LinkedList<>();
    stackB = new LinkedList<>();
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    stackA.addFirst(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    if (stackB.isEmpty()) merge();
    return stackB.removeFirst();
  }

  /** Get the front element. */
  public int peek() {
    if (stackB.isEmpty()) merge();
    return stackB.peekFirst();
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return stackA.isEmpty() && stackB.isEmpty();
  }

  public void merge() {
    while (!stackA.isEmpty()) stackB.addFirst(stackA.pop());
  }
}
