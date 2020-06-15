class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

public class L0109_LL {
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null || head.next == null) return head == null ? null : new TreeNode(head.val);
    return callDFS(head);
  }

  public ListNode getMid(ListNode head) {
    ListNode he = head;
    ListNode she = head;
    ListNode prevHe = null;
    while (she.next != null && she.next.next != null) {
      prevHe = he;
      he = he.next;
      she = she.next.next;
    }
    return prevHe == null ? he : prevHe;
  }

  public TreeNode callDFS(ListNode head) {
    if (head.next == null) return new TreeNode(head.val);
    ListNode prevHe = getMid(head);
    ListNode mid = prevHe.next;
    ListNode right = mid.next;
    TreeNode root = new TreeNode(mid.val);
    mid.next = null;
    prevHe.next = null;
    if (prevHe != null) root.left = callDFS(head);
    if (right != null) root.right = callDFS(right);
    return root;
  }
}
