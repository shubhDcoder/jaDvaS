import java.util.*;

public class L0872_TREE {
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

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    leafSimilarDFS(root1, ans1);
    leafSimilarDFS(root2, ans2);

    if (ans1.size() != ans2.size()) return false;

    for (int i = 0; i < ans1.size(); i++) {
      if (ans1.get(i) != ans2.get(i)) return false;
    }
    return true;
  }

  List<Integer> ans1 = new ArrayList<>();
  List<Integer> ans2 = new ArrayList<>();

  public void leafSimilarDFS(TreeNode root, List<Integer> ans) {
    if (root.left == null && root.right == null) ans.add(root.val);
    if (root.left != null) leafSimilarDFS(root.left, ans);
    if (root.right != null) leafSimilarDFS(root.right, ans);
  }
}
