import java.util.*;

public class L0094_TREE {
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

  public TreeNode getPredecessor(TreeNode node) {
    TreeNode pdscr = node.left;
    while (pdscr.right != node && pdscr.right != null) pdscr = pdscr.right;
    return pdscr;
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> answer = new LinkedList<>();
    TreeNode parent = root;

    while (parent != null) {
      if (parent.left == null) {
        answer.add(parent.val);
        parent = parent.right;
      } else {
        TreeNode predecessor = getPredecessor(parent);
        if (predecessor.right == null) {
          predecessor.right = parent;
          parent = parent.left;
        } else {
          predecessor.right = null;
          answer.add(parent.val);
          parent = parent.right;
        }
      }
    }
    return answer;
  }
}
