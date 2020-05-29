public class L0112_TREE {
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

  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;

    int decide = sum - root.val;
    if (root.left == null && root.right == null && decide == 0) return true;
    else return hasPathSum(root.left, decide) || hasPathSum(root.right, decide);
  }
}
