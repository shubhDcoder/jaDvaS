public class L1367_LL {
  ListNode supreme;

  public boolean isSubPath(ListNode head, TreeNode root) {
    if (root == null) return false;

    supreme = head;
    return callDFS(root, supreme);
  }

  public boolean callDFS(TreeNode root, ListNode current) {
    if (root == null) return false;
    if (root.val == current.val && current.next == null) return true;

    ListNode send;
    if (root.val == current.val) send = current.next;
    else if (root.val == supreme.val) send = supreme.next;
    else send = supreme;

    return callDFS(root.left, send) || callDFS(root.right, send);
  }
}
