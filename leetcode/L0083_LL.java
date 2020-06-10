public class L0083_LL {

  // 1 - 1 - 2 - 3 - 3
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;

    ListNode previous = head;
    ListNode temp = head.next;
    while (temp != null) {
      if (previous.val != temp.val) {
        previous.next = temp;
        previous = temp;
        temp = temp.next;
      } else {
        previous.next = null;
        ListNode temp0 = temp;
        temp = temp.next;
        temp0.next = null;
      }
    }

    return head;
  }
}
