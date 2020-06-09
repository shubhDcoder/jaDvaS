public class L0513_TREE {
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

  public int findBottomLeftValue(TreeNode root) {
    callDFS(root, 0);
    return leftMost.val;
  }

  int maxLevel = -1;
  TreeNode leftMost = null;

  public void callDFS(TreeNode root, int level) {
    if (root.left == null && root.right == null) {
      if (level > maxLevel) {
        leftMost = root;
        maxLevel = level;
      }
      return;
    }

    if (root.left != null) callDFS(root.left, level + 1);
    if (root.right != null) callDFS(root.right, level + 1);
  }
}
