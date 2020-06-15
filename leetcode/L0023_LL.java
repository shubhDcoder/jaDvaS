import java.util.*;

public class L0023_LL {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    if (lists.length == 1) return lists[0];
    PriorityQueue<ListNode> pQueue = new PriorityQueue<>();
    for (int i = 0; i < lists.length; i++) {
      pQueue.offer(lists[i]);
    }

    ListNode tempHead = new ListNode(-1);
    ListNode current = tempHead;
    while (pQueue.isEmpty() == false) {
      ListNode popped = pQueue.poll();
      current.next = popped;
      if (popped.next != null) pQueue.offer(popped.next);
    }

    return tempHead.next;
  }
}
