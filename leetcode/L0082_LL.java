public class L0082_LL {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode dummy = new ListNode();
    dummy.next = head;
    ListNode previous = dummy;
    ListNode current = head;
    while (current != null) {
      if (current.next != null && current.val == current.next.val) {
        while (current.next != null && current.val == current.next.val) {
          current = current.next;
        }
        ListNode temp = current.next;
        current.next = null;
        current = temp;
        continue;
      }
      previous.next = current;
      previous = current;
      current = current.next;
    }

    if (previous == dummy) previous.next = null;
    return dummy.next;
  }
}
