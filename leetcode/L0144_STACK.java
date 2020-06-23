import java.util.*;

public class L0144_STACK {
  public List<Integer> preorderTraversal(TreeNode root) {
    if (root == null) return new ArrayList<>();
    List<Integer> answer = new ArrayList<>();
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode popped = stack.pop();
      if (popped.right != null) stack.push(popped.right);
      if (popped.left != null) stack.push(popped.left);
      answer.add(popped.val);
    }
    return answer;
  }
}
