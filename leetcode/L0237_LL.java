public class L0237_LL {
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    ListNode delete = node.next;
    node.next = node.next.next;
    delete.next = null;
  }
}
