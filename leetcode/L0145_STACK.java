import java.util.*;

public class L0145_STACK {
  public List<Integer> postorderTraversal(TreeNode root) {
    Deque<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> queue = new LinkedList<>();
    if (root != null) stack.addFirst(root);
    while (!stack.isEmpty()) {
      TreeNode popped = stack.removeFirst();
      if (popped.left != null) stack.push(popped.left);
      if (popped.right != null) stack.push(popped.right);
      queue.addFirst(popped.val);
    }
    return queue;
  }
}
