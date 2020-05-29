import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class L0987_TREE {
  static class TreeNode implements Comparable<TreeNode> {
    int val;
    int vLine;
    int hLine; // for version 2
    TreeNode left, right;

    public TreeNode(int val) {
      this.val = val;
    }

    // for version 1
    // @Override
    // public int compareTo(TreeNode other) {
    //   if (this.vLine == other.vLine) return Integer.compare(this.val, other.val);
    //   return Integer.compare(this.vLine, other.vLine);
    // }

    @Override
    public int compareTo(TreeNode other) {
      if (this.hLine == other.hLine) {
        if (this.vLine == other.vLine) return Integer.compare(this.val, other.val);
        return Integer.compare(this.vLine, other.vLine);
      } else return Integer.compare(this.hLine, other.hLine);
    }
  }

  int index;

  public TreeNode constructTree(int[] array) {
    if (array[index] == -1 || index >= array.length) {
      index++;
      return null;
    }

    TreeNode root = new TreeNode(array[index++]);
    root.left = constructTree(array);
    root.right = constructTree(array);
    return root;
  }

  public static void main(String[] args) {
    int[] tree = {1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1};

    L0987_TREE obj = new L0987_TREE();
    TreeNode root = obj.constructTree(tree);

    // for (List<Integer> list : obj.verticalTraversal(root)) System.out.println(list);
    for (List<Integer> list : obj.verticalTraversal_V1(root)) System.out.println(list);
  }

  int min = Integer.MAX_VALUE;
  int max = Integer.MIN_VALUE;

  public void measureWidth(TreeNode root, int markWith) {
    if (root == null) return;
    root.vLine = markWith;
    min = Math.min(min, markWith);
    max = Math.max(max, markWith);
    measureWidth(root.left, markWith - 1);
    measureWidth(root.right, markWith + 1);
  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    measureWidth(root, 0);
    int totalWidth = Math.abs(min) + max + 1;
    List<List<Integer>> answer = new LinkedList<>();
    for (int i = 0; i < totalWidth; i++) answer.add(new LinkedList<>());

    PriorityQueue<TreeNode> parent = new PriorityQueue<>();
    PriorityQueue<TreeNode> child = new PriorityQueue<>();

    parent.offer(root);
    while (parent.isEmpty() == false) {
      int size = parent.size();
      while (size-- > 0) {
        TreeNode removed = parent.poll();
        answer.get(removed.vLine + Math.abs(min)).add(removed.val);
        if (removed.left != null) child.offer(removed.left);
        if (removed.right != null) child.offer(removed.right);
      }
      PriorityQueue<TreeNode> temp = parent;
      parent = child;
      child = temp;
    }

    return answer;
  }

  public void measureWidth_V1(TreeNode root, int markWith, int hLine) {
    if (root == null) return;
    root.vLine = markWith;
    root.hLine = hLine;
    min = Math.min(min, markWith);
    max = Math.max(max, markWith);
    measureWidth_V1(root.left, markWith - 1, hLine + 1);
    measureWidth_V1(root.right, markWith + 1, hLine + 1);
  }

  public List<List<Integer>> verticalTraversal_V1(TreeNode root) {
    measureWidth_V1(root, 0, 0);
    int totalWidth = Math.abs(min) + max + 1;
    List<List<Integer>> answer = new LinkedList<>();
    for (int i = 0; i < totalWidth; i++) answer.add(new LinkedList<>());

    PriorityQueue<TreeNode> queue = new PriorityQueue<>();
    queue.offer(root);
    while (queue.isEmpty() == false) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        answer.get(removed.vLine + Math.abs(min)).add(removed.val);
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
    }
    return answer;
  }
}
