public class L0083_LL {

  // 1 - 1 - 2 - 3 - 3
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;

    ListNode prev = head, current = head.next;

    while (current != null) {
      if (prev.val != current.val) {
        prev.next = current;
        prev = current;
      } else prev.next = null;
      current = current.next;
    }

    return head;
  }

  // old one

  public ListNode deleteDuplicates_v1(ListNode head) {
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

  public ListNode deleteDuplicates_v2(ListNode head) {
    ListNode node = head;
    while (node != null && node.next != null) {
      if (node.next.val == node.val) {
        node.next = node.next.next;
        ;
      } else {
        node = node.next;
      }
    }
    return head;
  }
}
