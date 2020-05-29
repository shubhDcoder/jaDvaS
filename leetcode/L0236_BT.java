public class L0236_BT {
  public static void main(String args[]) {}

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;

    if (root.val == p.val || root.val == q.val) return root;

    TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
    TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

    if (leftNode != null && rightNode != null) return root;
    else if (leftNode == null && rightNode == null) return null;
    else if (leftNode != null) return leftNode;
    else return rightNode;
  }
}
