public class L1026_TREE {
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

  int maxV = -1;

  public int maxAncestorDiff(TreeNode root) {
    maxAncestorDiff(root, root.val, root.val);
    return maxV;
  }

  public void maxAncestorDiff(TreeNode root, int max, int min) {
    if (root.val > max) max = root.val;
    if (root.val < min) min = root.val;
    if (root.left == null && root.right == null) {
      int diff = max - min;
      if (maxV < diff) maxV = diff;
      return;
    }
    if (root.left != null) maxAncestorDiff(root.left, max, min);
    if (root.right != null) maxAncestorDiff(root.right, max, min);
  }
}
