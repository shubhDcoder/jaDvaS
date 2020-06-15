public class L0203_LL {
  public ListNode removeElements(ListNode head, int val) {
    if (head == null) return null;

    while (head != null && head.val != val) head = head.next;
    if (head == null) return null;

    ListNode prev = head;
    ListNode curr = head.next;

    while (curr != null) {
      if (val != curr.val) {
        prev = curr;
      } else {
        prev.next = curr.next;
        ListNode temp = curr;
        curr = curr.next;
        temp.next = null;
      }
      curr = curr.next;
    }

    return prev;
  }
}
