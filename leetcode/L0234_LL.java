public class L0234_LL {
  int len = 0;
  int mid = 0;
  ListNode midNode = null;

  public boolean isPalindrome(ListNode head) {
    if (head == null) return true;
    ListNode temp = head;
    while (temp != null) {
      len++;
      temp = temp.next;
    }
    if (len == 1) return true;
    mid = (len >> 1) - 1;
    return callDFS(head, 0);
  }

  public boolean callDFS(ListNode node, int level) {
    if (level == mid) {
      if ((len & 1) == 1) midNode = node.next.next;
      else midNode = node.next;

      return midNode.val == node.val;
    }

    boolean ret = callDFS(node.next, level + 1);
    midNode = midNode.next;
    return ret && node.val == midNode.val;
  }
}
