public class L0876_LL {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  // she.next is for odd (odd will be always at middle)
  // she != null (one position after the mid)
  // she.next.next != null (one position before the mid)
  public ListNode middleNode(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode he = head, she = head.next.next;

    while (she != null && she.next != null) {
      he = he.next;
      she = she.next.next;
    }

    return he;
  }
}
