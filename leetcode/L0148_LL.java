public class L0148_LL {

  public ListNode getMid(ListNode node) {
    ListNode he = node, she = node;
    while (she.next != null && she.next.next != null) {
      he = he.next;
      she = she.next.next;
    }
    return he;
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode mid = getMid(head);
    ListNode send = mid.next;
    mid.next = null;

    return mergeTwoLists(sortList(head), sortList(send));
  }
}
