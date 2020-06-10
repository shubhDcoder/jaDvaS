public class L0160_LL {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;

    int lA = 1;
    int lB = 1;
    ListNode tempA = headA;
    ListNode tempB = headB;
    while (tempA.next != null) {
      tempA = tempA.next;
      lA++;
    }
    while (tempB.next != null) {
      tempB = tempB.next;
      lB++;
    }

    if (tempA != tempB) return null;

    if (lA > lB) {
      while (lA-- != lB) headA = headA.next;
    } else if (lB > lA) {
      while (lB-- != lA) headB = headB.next;
    }

    while (headA != headB) {
      headA = headA.next;
      headB = headB.next;
    }

    return headA;
  }
}
