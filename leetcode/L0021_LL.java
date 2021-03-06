public class L0021_LL {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) return l1 == null ? l2 : l1;

    ListNode newHead = new ListNode(-1);
    ListNode previous = newHead;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        previous.next = l1;
        while (l1 != null && l1.val <= l2.val) {
          previous = l1;
          l1 = l1.next;
        }
      } else {
        previous.next = l2;
        while (l2 != null && l2.val <= l1.val) {
          previous = l2;
          l2 = l2.next;
        }
      }
    }

    if (l1 != null) previous.next = l1;
    if (l2 != null) previous.next = l2;

    return newHead.next;
  }
}
