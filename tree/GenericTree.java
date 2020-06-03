import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenericTree {
  static class GNode {
    int data;
    List<GNode> childs = new ArrayList<>();

    public GNode(int data) {
      this.data = data;
    }

    public List<GNode> getChilds() {
      return childs;
    }

    @Override
    public String toString() {
      return String.valueOf(this.data);
    }
  }

  public static GNode constructTree(int[] input) {
    Deque<GNode> stack = new LinkedList<>();
    int index = 0;
    GNode root = new GNode(input[index++]);
    stack.push(root);

    while (index < input.length) {
      int nextVal = input[index++];
      if (nextVal != -1) {
        stack.push(new GNode(nextVal));
      } else {
        GNode child = stack.pop();
        if (stack.isEmpty()) break;
        stack.peek().getChilds().add(child);
      }
    }
    return root;
  }

  public static void preOrder(GNode root) {
    StringBuilder ans =
        new StringBuilder("childs of " + String.format("%-4d", root.data) + " are : ");
    for (GNode child : root.getChilds()) ans.append(child.data + " ");
    System.out.println(ans);
    for (GNode child : root.getChilds()) preOrder(child);
  }

  private static void flatten_helper(GNode root, LinkedList<GNode> answer) {
    if (root.getChilds().size() == 0) {
      answer.add(root);
      return;
    }
    answer.add(root);
    for (GNode node : root.getChilds()) {
      flatten_helper(node, answer);
      node.getChilds().clear();
    }
  }

  public static void flatten() {
    System.out.println("flatten : assigning every nodes to root ");
    LinkedList<GNode> answer = new LinkedList<>();
    GNode root = getTree();
    flatten_helper(root, answer);
    answer.removeFirst();
    root.childs = answer;
    preOrder(root);
    System.out.println();
  }

  public static int height(GNode root) {
    int height = 0;
    for (GNode n : root.getChilds()) height = Math.max(height, height(n));
    return height + 1;
  }

  public static GNode find(GNode root, int key) {
    if (root.data == key) return root;
    GNode ans = null;
    for (GNode n : root.getChilds()) {
      ans = find(n, key);
      if (ans != null) return ans;
    }
    return ans;
  }

  public static boolean isMirror(GNode rootA, GNode rootB) {
    int la = rootA.getChilds().size();
    int lb = rootB.getChilds().size();
    // System.out.println("checking " + rootA.data + " , " + rootB.data);
    if (la != lb || rootA.data != rootB.data) return false;
    boolean symmetric = true;
    for (int i = 0; i < la && symmetric; i++) {
      if (rootA == rootB && i > la / 2) break;
      symmetric = isMirror(rootA.getChilds().get(i), rootB.getChilds().get(la - 1 - i));
    }
    return symmetric;
  }

  public static void levelOrder(GNode root) {
    if (root == null) return;
    int level = 0;
    Queue<GNode> queue = new LinkedList<>();
    queue.offer(root);
    while (queue.isEmpty() == false) {
      System.out.print("level " + level + " : ");
      int size = queue.size();
      while (size-- > 0) {
        GNode popped = queue.poll();
        System.out.print(popped.data + " ");
        for (GNode node : popped.getChilds()) {
          queue.offer(node);
        }
      }
      System.out.println();
      level++;
    }
    System.out.println();
  }

  public static int getSize(GNode root) {
    int s = 0;
    for (GNode n : root.getChilds()) s += getSize(n);
    return s + 1;
  }

  public static boolean rootToNodePath(GNode root, int key, LinkedList<GNode> ans) {
    // System.out.println("checking " + root.data);
    if (root.data == key) {
      ans.addFirst(root);
      return true;
    }
    for (GNode node : root.getChilds()) {
      if (rootToNodePath(node, key, ans)) {
        ans.addFirst(root);
        return true;
      }
    }
    return false;
  }

  public static GNode lineralize_helper(GNode root) {
    int cSize = root.getChilds().size();
    if (cSize == 0) return root;
    GNode last = lineralize_helper(root.getChilds().get(cSize - 1));
    for (int i = cSize - 2; i >= 0; i--) {
      GNode sLast = lineralize_helper(root.getChilds().get(i));
      sLast.getChilds().add(root.getChilds().get(i + 1));
      root.getChilds().remove(i + 1);
    }
    return last;
  }

  public static void lineralize() {
    System.out.println("Linearization of General tree is");
    GNode root = getTree();
    GNode tail = lineralize_helper(root);
    preOrder(root);
    System.out.println("with head at " + root.data + " and tail at " + tail.data + "\n");
  }

  public static GNode getTree() {
    int[] gTree = {
      10, 20, 90, -1, 40, 120, -1, -1, 50, -1, -1, 80, -1, 30, 60, -1, 70, 110, 150, -1, -1, -1,
      100, -1, -1, -1
    };
    return constructTree(gTree);
  }

  public static void genericOperations() {
    GNode root = getTree();
    System.out.println("\nheight of tree is : " + height(root));
    GNode found = find(root, 50);
    System.out.println("find item 50 : " + found + "\n");
    System.out.println("level order is = >");
    levelOrder(root);
    System.out.println("size of tree is " + getSize(root));
  }

  public static void isMirror() {
    int[] gTreeS = {
      10, 20, 50, -1, 60, 110, -1, 120, -1, 130, -1, -1, -1, 40, 90, -1, 80, 100, -1, -1, 90, -1,
      -1, 20, 60, 130, -1, 120, -1, 110, -1, -1, 50, -1, -1, -1
    };
    GNode rootS = constructTree(gTreeS);
    System.out.println("If the given tree is mirror ");
    preOrder(rootS);
    System.out.println("IS mirror => " + isMirror(rootS, rootS) + "\n");
  }

  public static void rootToNodePath() {
    LinkedList<GNode> path = new LinkedList<>();
    GNode root = getTree();
    rootToNodePath(root, 150, path);
    System.out.println("root to node path for 150");
    path.stream().forEach(e -> System.out.print(e.data + " "));
    System.out.println();
  }

  public static void main(String[] args) {
    System.out.println();
    lineralize();
    flatten();
    isMirror();
    rootToNodePath();
    genericOperations();
  }
}
