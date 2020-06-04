public class L0110_TREE {
  public class TreeNode {
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

  public boolean isBalanced(TreeNode root) {
    if (isBalanced_(root) == Integer.MIN_VALUE) return false;
    return true;
  }

  public int isBalanced_(TreeNode root) {
    if (root == null) return -1;
    int lh = isBalanced_(root.left);
    if (lh == Integer.MIN_VALUE) return lh;
    int rh = isBalanced_(root.right);
    if (rh == Integer.MIN_VALUE) return rh;
    if (Math.abs(lh - rh) > 1) return Integer.MIN_VALUE;
    return Math.max(lh, rh) + 1;
  }
}
