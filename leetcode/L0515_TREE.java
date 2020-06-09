import java.util.*;

public class L0515_TREE {
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

  public List<Integer> largestValues(TreeNode root) {
    List<Integer> answer = new ArrayList<>();
    answer.add(root.val);
    callDFS(root, 0, answer);
    return answer;
  }

  public void callDFS(TreeNode root, int level, List<Integer> answer) {
    if (root == null) return;
    if (answer.size() < level) answer.add(-1);
    if (answer.get(level) < root.val) answer.set(level, root.val);
    callDFS(root.left, level + 1, answer);
    callDFS(root.right, level + 1, answer);
  }
}
