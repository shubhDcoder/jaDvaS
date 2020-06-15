public class L0025_LL {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k == 1) return head;

    int tempK = k;

    ListNode toBeTail = head;
    ListNode prev = null;
    ListNode curr = head;
    ListNode next = null;
    while (tempK-- > 0) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    ListNode tempHead = prev;

    while (curr != null) {
      tempK = k;
      ListNode tempCurr = curr;
      next = null;
      prev = null;
      while (curr != null && tempK-- > 0) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }

      if (curr == null && tempK != 0) {
        next = null;
        curr = prev;
        prev = null;
        while (k - ++tempK != 0) {
          next = curr.next;
          curr.next = prev;
          prev = curr;
          curr = next;
        }
        toBeTail.next = prev;
        break;
      }
      toBeTail.next = prev;
      toBeTail = tempCurr;
    }

    return tempHead;
  }
}
