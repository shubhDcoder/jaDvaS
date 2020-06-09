import java.util.*;

public class L0199_TREE {
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

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> answer = new LinkedList<>();
    callDFS(root, 0, answer);
    return answer;
  }

  public void callDFS(TreeNode root, int level, List<Integer> answer) {
    if (root == null) return;
    if (answer.size() == level) answer.add(root.val);
    callDFS(root.right, level + 1, answer);
    callDFS(root.left, level + 1, answer);
  }

  /*   public List<Integer> rightSideView(TreeNode root) {
    List<Integer> answer = new LinkedList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (queue.isEmpty() == false) {
      int size = queue.size();
      TreeNode prev = null;
      while (size-- > 0) {
        TreeNode popped = queue.poll();
        prev = popped;
        if (popped.left != null) queue.offer(popped.left);
        if (popped.right != null) queue.offer(popped.right);
      }
      answer.add(prev.val);
    }
    return answer;
  } */
}
