public class L0141_LL {
  public boolean hasCycle(ListNode head) {
    if (head == null) return false;

    ListNode singleM = head;
    ListNode doubleM = head.next;

    while (singleM != null && doubleM != null) {
      if (singleM.val == doubleM.val) return true;
      singleM = singleM.next;

      if (doubleM.next != null) doubleM = doubleM.next.next;
      else return false;
    }
    return false;
  }
}
