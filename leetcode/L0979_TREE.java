public class L0979_TREE {
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

  int moves = 0;

  public int distributeCoins(TreeNode root) {
    if (root == null) return 0;
    int left = distributeCoins(root.left);
    int right = distributeCoins(root.right);
    moves += Math.abs(left) + Math.abs(right);
    return left + right + root.val - 1;
  }
}
