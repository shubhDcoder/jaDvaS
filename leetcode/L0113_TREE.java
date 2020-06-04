import java.util.*;

public class L0113_TREE {
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

  List<List<Integer>> answer = new LinkedList<>();
  Deque<Integer> trace = new LinkedList<>();

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    traceAllSums(root, sum);
    return answer;
  }

  public void traceAllSums(TreeNode root, int sum) {
    if (root == null) return;

    trace.addFirst(root.val);

    int decide = sum - root.val;
    if (root.left == null && root.right == null && decide == 0) {
      answer.add(new LinkedList<>(trace));
      trace.removeFirst();
      return;
    }

    traceAllSums(root.left, decide);
    traceAllSums(root.right, decide);
  }
}
