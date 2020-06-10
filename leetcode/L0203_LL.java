public class L0203_LL {
  public ListNode removeElements(ListNode head, int val) {
    while (head != null && head.val == val) head = head.next;
    if (head == null) return null;

    ListNode prev = head;
    ListNode curr = head.next;
    while (curr != null) {
      if (curr.val == val) {
        prev.next = null;
        curr = curr.next;
        continue;
      }
      prev.next = curr;
      prev = curr;
      curr = curr.next;
    }
    return head;
  }
}
