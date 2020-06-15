public class L0019_LL {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || head.next == null) return null;

    ListNode end = head;
    while (n-- > 0) end = end.next;
    if (end == null) return head.next;

    ListNode start = head;
    while (end.next != null) {
      start = start.next;
      end = end.next;
    }
    ListNode delete = start.next;
    start.next = start.next.next;
    delete.next = null;

    return head;
  }
}
