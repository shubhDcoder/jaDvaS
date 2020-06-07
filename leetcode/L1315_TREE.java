public class L1315_TREE {
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

  int sum;

  public int sumEvenGrandparent(TreeNode root) {
    callDFS(root);
    return sum;
  }

  public void callDFS(TreeNode root) {
    if (root == null) return;
    if ((root.val & 1) == 0) {
      if (root.left != null && root.left.left != null) sum += root.left.left.val;
      if (root.left != null && root.left.right != null) sum += root.left.right.val;
      if (root.right != null && root.right.right != null) sum += root.right.right.val;
      if (root.right != null && root.right.left != null) sum += root.right.left.val;
    }
    callDFS(root.left);
    callDFS(root.right);
  }
}
