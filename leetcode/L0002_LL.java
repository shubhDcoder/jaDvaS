public class L0002_LL {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode tempHead = new ListNode();
    ListNode head = tempHead;
    int carry = 0;
    while (l1 != null || l2 != null || carry > 0) {
      int a = 0, b = 0;
      if (l1 != null) {
        a = l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        b = l2.val;
        l2 = l2.next;
      }
      int result = a + b + carry;
      carry = result / 10;
      int store = result % 10;
      ListNode node = new ListNode(store);
      tempHead.next = node;
      tempHead = node;
    }
    return head.next;
  }
}
