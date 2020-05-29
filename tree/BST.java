import java.util.*;

public class BST {

  static class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return String.valueOf(data);
    }
  }

  static int idx = 0;

  public static Node constructGraph(int[] arr) {
    if (idx == arr.length || arr[idx] == -1) {
      idx++;
      return null;
    }

    Node root = new Node(arr[idx++]);
    root.left = constructGraph(arr);
    root.right = constructGraph(arr);

    return root;
  }

  public static void display(Node node) {
    if (node == null) return;

    String s1, s2, s3 = "";
    s1 = (node.left != null) ? String.format("%-5d", node.left.data) : String.format("%-5s", " ");
    s2 = String.format("%-4s%-3d%-4s", "<-", node.data, "->");
    s3 = (node.right != null) ? String.format("%-5d", node.right.data) : String.format("%-5s", " ");

    System.out.println(s1 + s2 + s3);
    display(node.left);
    display(node.right);
  }

  public static int getSize(Node root) {
    if (root == null) return 0;
    return getSize(root.left) + getSize(root.right) + 1;
  }

  public static List<Node> ans = new ArrayList<>();

  public static boolean getRootToNode(Node root, int key) {
    if (root == null) return false;
    if (root.data == key || getRootToNode(root.left, key) || getRootToNode(root.right, key)) {
      ans.add(root);
      return true;
    }
    return false;
  }

  static boolean found = false;

  public static Node lowestCommonAncestor(Node root, Node p, Node q) {
    if (root == null) return null;

    if (root.data == p.data) {
      return p;
    } else if (root.data == q.data) {
      return q;
    }

    Node left = lowestCommonAncestor(root.left, p, q);
    Node right = null;
    if (found == false) right = lowestCommonAncestor(root.right, p, q);

    if (left != null && right != null) {
      found = true;
      return root;
    }
    if (left != null) return left;
    if (right != null) return right;
    return null;
  }

  public static void main(String args[]) {
    int[] tree = {
      10, 20, 3, 1, -1, -1, 5, -1, -1, 21, -1, 24, -1, -1, 40, 35, -1, -1, 55, 50, -1, -1, 80, -1,
      -1
    };
    Node root = constructGraph(tree);
    display(root);
    System.out.println("Size of BT is " + getSize(root));
    getRootToNode(root, 80);
    System.out.println(ans.toString());
    System.out.println(lowestCommonAncestor(root, new Node(20), new Node(24)));
  }
}
