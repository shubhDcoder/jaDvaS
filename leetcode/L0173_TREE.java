import java.util.Deque;
import java.util.LinkedList;

public class L0173_TREE {
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

  Deque<TreeNode> stack = new LinkedList<>();

  public void fillStack(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  public L0173_TREE(TreeNode root) {
    fillStack(root);
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode popped = stack.pop();
    fillStack(popped.right);
    return popped.val;
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }
}
