import java.util.*;

public class L0257_TREE {
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

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new LinkedList<>();
    if (root == null) return paths;
    binaryTreePaths(root, "", paths);
    return paths;
  }

  public void binaryTreePaths(TreeNode root, String ans, List<String> paths) {
    if (root.left == null && root.right == null) {
      paths.add(ans + root.val);
      return;
    }
    if (root.left != null) binaryTreePaths(root.left, ans + root.val + "->", paths);
    if (root.right != null) binaryTreePaths(root.right, ans + root.val + "->", paths);
  }
}
