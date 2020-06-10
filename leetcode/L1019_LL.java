import java.util.Deque;
import java.util.LinkedList;

public class L1019_LL {
  public int[] nextLargerNodes(ListNode head) {
    if (head == null) return new int[0];
    if (head.next == null) return new int[] {head.val};

    Deque<Integer> stackA = new LinkedList<>();
    Deque<Integer> stackB = new LinkedList<>();

    ListNode temp = head;
    int len = 0;
    while (temp != null) {
      stackA.push(temp.val);
      temp = temp.next;
      len++;
    }

    int answer[] = new int[len];

    while (stackA.isEmpty() == false) {
      int popped = stackA.pop();
      if (stackB.isEmpty()) {
        answer[--len] = 0;
        stackB.push(popped);
      } else {
        while (!stackB.isEmpty() && stackB.peek() < popped) stackB.pop();
        if (stackB.isEmpty()) answer[--len] = 0;
        else answer[--len] = stackB.peek();
        stackB.push(popped);
      }
    }

    return answer;
  }
}
