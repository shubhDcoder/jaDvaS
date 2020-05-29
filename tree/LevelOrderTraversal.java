import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
  static class TreeNode {
    int val;
    int vLine;
    TreeNode left, right;

    public TreeNode(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return String.valueOf(val);
    }
  }

  public static int index;

  public static TreeNode constructGraph(int[] array) {
    if (array[index] == -1) {
      index++;
      return null;
    }

    TreeNode node = new TreeNode(array[index++]);
    node.left = constructGraph(array);
    node.right = constructGraph(array);
    return node;
  }

  public static void display(TreeNode node) {
    if (node == null) return;

    String s1, s2, s3 = "";
    s1 = (node.left != null) ? String.format("%-5d", node.left.val) : String.format("%-5s", " ");
    s2 = String.format("%-4s%-3d%-4s", "<-", node.val, "->");
    s3 = (node.right != null) ? String.format("%-5d", node.right.val) : String.format("%-5s", " ");

    System.out.println(s1 + s2 + s3);
    display(node.left);
    display(node.right);
  }

  public static void levelOrderTraversal(TreeNode root) {
    Queue<TreeNode> parent = new LinkedList<>();
    Queue<TreeNode> child = new LinkedList<>();

    parent.add(root);

    while (parent.size() > 0 || child.size() > 0) {
      if (parent.size() > 0) {
        while (parent.size() > 0) {
          TreeNode popped = parent.poll();
          System.out.print(popped.val + " ");
          if (popped.left != null) child.add(popped.left);
          if (popped.right != null) child.add(popped.right);
        }
      } else if (child.size() > 0) {
        while (child.size() > 0) {
          TreeNode popped = child.poll();
          System.out.print(popped.val + " ");
          if (popped.left != null) parent.add(popped.left);
          if (popped.right != null) parent.add(popped.right);
        }
      }

      System.out.println();
    }
  }

  public static void levelOrderTraversal_02(TreeNode root) {
    Queue<TreeNode> parent = new LinkedList<>();
    Queue<TreeNode> child = new LinkedList<>();

    parent.add(root);

    while (parent.size() > 0) {
      while (parent.size() > 0) {
        TreeNode popped = parent.poll();
        System.out.print(popped.val + " ");
        if (popped.left != null) child.add(popped.left);
        if (popped.right != null) child.add(popped.right);
      }
      System.out.println();
      Queue<TreeNode> temp = parent;
      parent = child;
      child = temp;
    }
  }

  public static void levelOrderTraversal_03(TreeNode root) {
    Queue<TreeNode> parent = new LinkedList<>();

    parent.add(root);

    int level = 0;
    while (parent.size() > 0) {
      int size = parent.size();
      System.out.print("level : " + level + " - ");
      while (size-- > 0) {
        TreeNode popped = parent.poll();
        System.out.print(popped.val + " ");
        if (popped.left != null) parent.add(popped.left);
        if (popped.right != null) parent.add(popped.right);
      }
      level++;
      System.out.println();
    }
  }

  public static void levelOrderTraversal_04(TreeNode root) {
    Queue<TreeNode> parent = new LinkedList<>();

    parent.offer(root);
    parent.offer(null);

    int level = 0;
    while (parent.size() > 1) {
      TreeNode popped = parent.poll();

      if (popped == null) {
        level++;
        System.out.println();
        parent.offer(null);
        continue;
      }

      System.out.print(popped.val + " ");
      if (popped.left != null) parent.add(popped.left);
      if (popped.right != null) parent.add(popped.right);
    }
    System.out.println();
  }

  static int min = Integer.MAX_VALUE;
  static int max = Integer.MIN_VALUE;

  public static void verticalView(TreeNode root, int markWith) {
    if (root == null) return;
    root.vLine = markWith;
    min = Math.min(min, markWith);
    max = Math.max(max, markWith);
    verticalView(root.left, markWith - 1);
    verticalView(root.right, markWith + 1);
  }

  public static void verticalView(TreeNode root) {
    verticalView(root, 0);
    int totalWidth = Math.abs(min) + max + 1;
    List<List<Integer>> holder = new ArrayList<>();
    for (int i = 0; i < totalWidth; i++) holder.add(new ArrayList<>());

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (queue.size() > 0) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        holder.get(removed.vLine + Math.abs(min)).add(removed.val);
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
    }

    for (List<Integer> list : holder) System.out.println(list);
  }

  public static void verticalView_WithSum(TreeNode root) {
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    verticalView(root, 0);
    int totalWidth = Math.abs(min) + max + 1;
    List<Integer> holder = new ArrayList<>();
    for (int i = 0; i < totalWidth; i++) holder.add(0);

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (queue.size() > 0) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        int index = removed.vLine + Math.abs(min);
        holder.set(index, removed.val + holder.get(index));
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
    }

    for (int sum : holder) System.out.println(sum);
  }

  public static void leftView(TreeNode node) {
    if (node == null) return;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(node);

    int level = 0;
    while (queue.size() > 0) {
      int size = queue.size();
      System.out.println("level " + level + " : " + queue.peek());
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
      level++;
    }
  }

  public static void rightView(TreeNode node) {
    if (node == null) return;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(node);

    int level = 0;
    while (queue.size() > 0) {
      int size = queue.size();
      TreeNode prev = null;
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
        prev = removed;
      }
      System.out.println("level " + level + " : " + prev);
      level++;
    }
  }

  public static int[] bottomView(TreeNode node) {
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    verticalView(node, 0);
    int totalWidth = Math.abs(min) + max + 1;

    int[] answer = new int[totalWidth];
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(node);

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        answer[Math.abs(min) + removed.vLine] = removed.val;
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
    }
    return answer;
  }

  public static int[] topView(TreeNode node) {
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    verticalView(node, 0);

    int totalWidth = Math.abs(min) + max + 1;
    int[] answer = new int[totalWidth];
    Arrays.fill(answer, Integer.MAX_VALUE);

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(node);
    while (queue.isEmpty() == false) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        int index = Math.abs(min) + removed.vLine;
        if (answer[index] == Integer.MAX_VALUE) answer[index] = removed.val;
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
    }
    return answer;
  }

  public static void diagonalView(TreeNode root) {
    List<List<TreeNode>> answer = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();

    TreeNode tempRight = root;
    while (tempRight != null) {
      queue.offer(tempRight);
      tempRight = tempRight.right;
    }

    while (!queue.isEmpty()) {
      answer.add(new ArrayList<TreeNode>(queue));
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        removed = removed.left;
        while (removed != null) {
          queue.offer(removed);
          removed = removed.right;
        }
      }
    }

    for (List<TreeNode> list : answer) syso(list);
  }

  public static void view_diagonal(TreeNode node, int markWith) {
    if (node == null) return;
    node.vLine = markWith;
    min = Math.min(min, markWith);
    view_diagonal(node.left, markWith - 1);
    view_diagonal(node.right, markWith);
  }

  public static void view_diagonal(TreeNode node) {
    if (node == null) return;
    min = Integer.MAX_VALUE;
    view_diagonal(node, 0);

    int totalWidth = Math.abs(min) + 1;

    List<List<Integer>> answer = new ArrayList<>();
    for (int i = 0; i < totalWidth; i++) answer.add(new ArrayList<>());
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(node);
    while (queue.isEmpty() == false) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode removed = queue.poll();
        answer.get(Math.abs(removed.vLine)).add(removed.val);
        if (removed.left != null) queue.offer(removed.left);
        if (removed.right != null) queue.offer(removed.right);
      }
    }

    for (List<Integer> list : answer) syso(list);
  }

  public static void main(String args[]) {
    int[] tree = {
      10, 20, 3, 1, -1, -1, 5, -1, -1, 21, -1, -1, 40, 35, -1, 24, -1, -1, 55, 50, -1, -1, 80, -1,
      -1
    };
    TreeNode root = constructGraph(tree);
    markHeading("LEVEL ORDER TRAVERSAL OF BINARY TREE");

    // syso("grph looks like");
    // printLine();
    // display(root);
    // printLine();
    // syso("with No swapping");
    // printLine();
    // levelOrderTraversal(root);
    // printLine();
    // syso("with swapping");
    // printLine();
    // levelOrderTraversal_02(root);
    // printLine();
    // syso("with Old BFS way");
    // printLine();
    // levelOrderTraversal_03(root);
    // printLine();
    // syso("with Old BFS Delimiter way");
    // printLine();
    // levelOrderTraversal_04(root);
    // printLine();
    // syso("Vertical top view");
    // printLine();
    // verticalView(root);
    // printLine();
    // syso("Vertical top view with sum");
    // printLine();
    // verticalView_WithSum(root);
    // printLine();
    // syso("Left view");
    // printLine();
    // leftView(root);
    // printLine();
    // syso("Right view");
    // printLine();
    // rightView(root);
    // printLine();
    // syso("bottom view");
    // printLine();
    // syso(Arrays.toString(bottomView(root)));
    // syso("Top view");
    // printLine();
    // syso(Arrays.toString(topView(root)));
    syso("Diagonal view one pass");
    printLine();
    diagonalView(root);
    printLine();
    syso("Diagonal view two pass");
    printLine();
    view_diagonal(root);
    printLine();
  }

  // random utility methods
  public static final int FIXED_LEN = 135;

  public static void printLine() {
    System.out.println(String.format("%140s", " ").replaceAll(" ", "*"));
  }

  public static void printLine(String word) {
    printNewLine();
    int write = word.length();
    int fill = (FIXED_LEN - write) / 2;
    System.out.println(
        String.format("%" + fill + "s", " ").replaceAll(" ", "*")
            + " : "
            + word
            + " : "
            + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "*"));
  }

  public static void printNewLine() {
    System.out.println();
  }

  public static void syso(Object obj) {
    System.out.println(obj);
  }

  public static void markHeading(String string) {
    printNewLine();
    printLine();
    syso(string);
    printLine();
    printNewLine();
  }
}
