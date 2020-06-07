import java.util.*;

public class L1123_TREE {
  static class TreeNode {
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

  byte[] isRemoved;
  List<TreeNode> ans = new LinkedList<>();

  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    isRemoved = new byte[to_delete.length];
    for (int k : to_delete) isRemoved[k] = 1;
    TreeNode ret = callDFS(root);
    if (ret != null) ans.add(ret);
    return ans;
  }

  public TreeNode callDFS(TreeNode root) {
    if (root == null) return null;
    TreeNode left = callDFS(root.left);
    TreeNode right = callDFS(root.right);

    if (isRemoved[root.val] == 1) {
      root.left = null;
      root.right = null;
      if (left != null) ans.add(left);
      if (right != null) ans.add(right);
      return null;
    }
    root.left = left;
    root.right = right;
    return root;
  }
}
