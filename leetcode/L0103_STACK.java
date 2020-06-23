import java.util.*;

public class L0103_STACK {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> answer = new LinkedList<>();
    Deque<TreeNode> stackA = new LinkedList<>();
    Deque<TreeNode> stackB = new LinkedList<>();
    if (root != null) stackA.addFirst(root);
    while (!stackA.isEmpty() || !stackB.isEmpty()) {
      List<Integer> tempAnswer = new LinkedList<>();
      if (!stackA.isEmpty()) {
        while (!stackA.isEmpty()) {
          TreeNode popped = stackA.pop();
          if (popped.left != null) stackB.addFirst(popped.left);
          if (popped.right != null) stackB.addFirst(popped.right);
          tempAnswer.add(popped.val);
        }
      } else {
        while (!stackB.isEmpty()) {
          TreeNode popped = stackB.pop();
          if (popped.right != null) stackA.addFirst(popped.right);
          if (popped.left != null) stackA.addFirst(popped.left);
          tempAnswer.add(popped.val);
        }
      }
      answer.add(tempAnswer);
    }
    return answer;
  }
}
