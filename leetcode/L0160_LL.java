public class L0160_LL {
  public ListNode getIntersectionNode_v1(ListNode headA, ListNode headB) {
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

  // Do it with cycle detection

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    if (headA.next == null && headB.next == null) return null;

    ListNode tempHeadA = headA;
    ListNode tempHeadB = headB;
    while (tempHeadA.next != null) {
      tempHeadA = tempHeadA.next;
    }
    tempHeadA.next = headA;

    ListNode he = headB;
    ListNode she = headB;
    while (she != null && she.next != null) {
      he = he.next;
      she = she.next.next;
      if (he == she) break;
    }

    while (she != tempHeadB) {
      she = she.next;
      tempHeadB = tempHeadB.next;
    }

    return tempHeadB;
  }
}
