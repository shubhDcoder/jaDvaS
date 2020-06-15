public class L0086_LL {

  // Input: head = 1->4->3->2->5->2, x = 3
  // Output:       1->2->2->4->3->5
  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) return head;
    ListNode dummy = new ListNode();
    ListNode previous = dummy;
    dummy.next = head;
    while (head != null) {
      if (head.val >= x) {
        previous.next = head.next;
        ListNode secondListNode = head;
        head = head.next;
        secondListNode.next = null;
        addLast(secondListNode);
        continue;
      }
      previous = head;
      head = head.next;
    }
    if (dummy.next != null) {
      previous.next = dummySecond;
      return dummy.next;
    } else {
      return dummySecond.next;
    }
  }

  ListNode dummySecond = new ListNode();
  ListNode secondTail = dummySecond;

  public void addLast(ListNode node) {
    secondTail.next = node;
    secondTail = secondTail.next;
  }
}
