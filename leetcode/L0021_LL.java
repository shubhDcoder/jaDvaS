public class L0021_LL {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) return l1 == null ? l2 : l1;

    ListNode pointerA = l1;
    ListNode pointerB = l2;

    ListNode head = (l1.val > l2.val ? l2 : l1);

    ListNode prev = null;
    while (pointerA != null && pointerB != null) {
      if (pointerA.val <= pointerB.val) {
        while (pointerA != null && pointerA.val <= pointerB.val) {
          prev = pointerA;
          pointerA = pointerA.next;
        }
        prev.next = pointerB;
      } else {
        while (pointerB != null && pointerB.val <= pointerA.val) {
          prev = pointerB;
          pointerB = pointerB.next;
        }
        prev.next = pointerA;
      }
    }

    if (pointerA == null) prev.next = pointerB;
    if (pointerB == null) prev.next = pointerA;

    return head;
  }
}
