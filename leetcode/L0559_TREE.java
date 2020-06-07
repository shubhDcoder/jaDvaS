import java.util.*;

public class L0559_TREE {
  class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  public int maxDepth(Node root) {
    if (root == null) return 0;
    return maxDepth_(root);
  }

  public int maxDepth_(Node root) {
    if (root.children.size() == 0) return 1;
    int h = -1;
    for (Node child : root.children) {
      h = Math.max(h, maxDepth_(child));
    }
    return h + 1;
  }
}
