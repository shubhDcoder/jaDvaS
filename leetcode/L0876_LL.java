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

  public ListNode middleNode(ListNode head) {
    ListNode he = head, she = head;
    while (she.next != null) {
      he = he.next;
      she = she.next.next;
    }
    return he;
  }
}
