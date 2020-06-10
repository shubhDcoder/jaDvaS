public class L0206_LL {
  ListNode newHead;

  public ListNode reverseList(ListNode head) {
    if (head == null) return null;
    reverseList_(head);
    head.next = null;
    return newHead;
  }

  public ListNode reverseList_(ListNode head) {
    if (head.next == null) {
      newHead = head;
      return head;
    }
    ListNode ret = reverseList_(head.next);
    ret.next = head;
    return head;
  }

  public ListNode reverseListIterative(ListNode head) {
    if (head == null) return null;

    ListNode previous = null;
    ListNode current = head;
    ListNode next = current.next;

    while (current != null) {
      current.next = previous;
      previous = current;
      current = next;
      if (next != null) next = next.next;
    }

    return previous;
  }
}
