public class L1145_TREE {
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

  int left, right, X;

  public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
    callDFS(root);
    int parent = n - 1 - left - right;
    int max = Math.max(parent, Math.max(left, right));
    int min = 0;
    if (max != parent) min += parent;
    if (max != left) min += left;
    if (max != right) min += right;
    return max > min;
  }

  public int callDFS(TreeNode node) {
    if (node == null) return 0;

    int l = callDFS(node.left), r = callDFS(node.right);
    if (node.val == X) {
      left = l;
      right = r;
    }
    return l + r + 1;
  }
}
