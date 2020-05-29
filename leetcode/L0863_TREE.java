import java.util.*;

public class L0863_TREE {
  public static void main(String args[]) {}

  // Definition for a binary tree node.
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // approach 1... using more space

  public List<TreeNode> path = new ArrayList<>();

  public boolean getRootToNodePath(TreeNode root, TreeNode dest) {
    if (root == null) return false;
    if (root.val == dest.val
        || getRootToNodePath(root.left, dest)
        || getRootToNodePath(root.right, dest)) {
      path.add(root);
      return true;
    }
    return false;
  }

  public void getAnswer(TreeNode node, int dist, List<Integer> ans, TreeNode prev) {
    if (node == null) return;
    if (prev != null && node.val == prev.val) return;
    if (dist == 0) ans.add(node.val);
    getAnswer(node.left, dist - 1, ans, prev);
    getAnswer(node.right, dist - 1, ans, prev);
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    getRootToNodePath(root, target);

    List<Integer> ans = new ArrayList<>();
    TreeNode prev = null;
    for (int i = 0; i < path.size(); i++) {
      if (K - i < 0) break;
      getAnswer(path.get(i), K - i, ans, prev);
      prev = path.get(i);
    }
    return ans;
  }

  List<Integer> answer = new ArrayList<>();

  public void markAtDist(TreeNode root, int K) {
    if (root == null || K < 0) return;
    if (K == 0) {
      answer.add(root.val);
      return;
    }
    markAtDist(root.left, K - 1);
    markAtDist(root.right, K - 1);
  }

  public int findElements(TreeNode root, TreeNode target, int K) {
    if (root == null) return -1;
    if (root.val == target.val) {
      markAtDist(target, K);
      return K - 1;
    }

    int left = findElements(root.left, target, K);
    if (left > 0) {
      markAtDist(root.right, left - 1);
      return left - 1;
    }

    int right = findElements(root.right, target, K);
    if (right > 0) {
      markAtDist(root.left, right - 1);
      return right - 1;
    }

    if (left == 0 || right == 0) {
      answer.add(root.val);
    }

    return -1;
  }
}
