public class L00092_LL {
  public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null || head.next == null) return head;

    int start = 1;
    ListNode previous = null;
    ListNode current = head;
    while (start < m) {
      previous = current;
      current = current.next;
      start++;
    }

    ListNode prev = null;
    ListNode curr = current;
    ListNode next = null;
    while (start <= n) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
      start++;
    }

    if (previous != null) previous.next = prev;
    else head = prev;

    current.next = curr;

    return head;
  }
}
