public class L1448_TREE {
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

  int ans;

  public int goodNodes(TreeNode root) {
    if (root == null) return 0;
    callDFS(root, root.val);
    return ans;
  }

  public void callDFS(TreeNode node, int maxTill) {
    if (node == null) return;
    if (node.val >= maxTill) {
      ans += 1;
      maxTill = node.val;
    }
    callDFS(node.left, maxTill);
    callDFS(node.right, maxTill);
  }
}
