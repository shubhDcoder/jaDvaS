public class L0142_LL {
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == head) return head;
    if (head.next == null) return null;
    ListNode he = head;
    ListNode she = head;
    while (she != null && she.next != null) {
      he = he.next;
      she = she.next.next;
      if (he == she) break;
    }

    if (he != she) return null;

    while (head != null) {
      if (he == head) break;
      head = head.next;
      he = he.next;
    }
    return head;
  }
}
