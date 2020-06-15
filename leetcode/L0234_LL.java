public class L0234_LL {
  int len = 0;
  int mid = 0;
  ListNode midNode = null;

  /* public boolean isPalindrome(ListNode head) {
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
  } */

  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;

    ListNode he = head, she = head;
    while (she.next != null && she.next.next != null) {
      he = he.next;
      she = she.next.next;
    }

    ListNode prev = null;
    ListNode curr = he.next;
    he.next = null;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    while (head != null && prev != null) {
      if (head.val != prev.val) return false;
      head = head.next;
      prev = prev.next;
    }

    return true;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 1};
    ListNode head = new ListNode(arr[0]);
    ListNode temp = head;
    for (int i = 1; i < arr.length; i++) {
      temp.next = new ListNode(arr[i]);
      temp = temp.next;
    }
    new L0234_LL().isPalindrome(head);
  }
}
