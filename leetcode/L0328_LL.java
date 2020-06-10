public class L0328_LL {

  ListNode headA, headB, tailA, tailB;

  public ListNode oddEvenList(ListNode head) {
    if (head == null) return null;
    callDFS(head, 1);
    tailA.next = headB;
    if (tailB != null) tailB.next = null;
    return headA;
  }

  public void callDFS(ListNode head, int level) {
    if (head == null) return;
    if ((level & 1) == 1) {
      if (headA == null) {
        headA = head;
        tailA = head;
      } else {
        tailA.next = head;
        tailA = head;
      }
    } else {
      if (headB == null) {
        headB = head;
        tailB = head;
      } else {
        tailB.next = head;
        tailB = head;
      }
    }
    callDFS(head.next, level + 1);
  }
}
