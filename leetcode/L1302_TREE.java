public class L1302_TREE {
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

  public int deepestLeavesSum(TreeNode root) {
    if (root == null) return 0;
    int hgth = height(root);
    callDFS(root, hgth, 0);
    return sum;
  }

  int sum = 0;

  public void callDFS(TreeNode root, int height, int currHeight) {
    if (root == null) return;
    if (root.left == null && root.right == null && height == currHeight) {
      sum += root.val;
      return;
    }

    callDFS(root.left, height, currHeight + 1);
    callDFS(root.right, height, currHeight + 1);
  }

  public int height(TreeNode root) {
    if (root == null) return -1;
    return Math.max(height(root.left), height(root.right)) + 1;
  }
}
