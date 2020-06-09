import java.util.Deque;
import java.util.LinkedList;

public class ExtraTree {
  public static class Node {
    int val;
    boolean sd, ld, rd;
    Node left, right;

    public Node(int val, boolean sd, boolean ld, boolean rd) {
      this.val = val;
      this.sd = sd;
      this.ld = ld;
      this.rd = rd;
    }

    public Node(int val) {
      this.val = val;
    }
  }

  public static void traverseInOrder(Node node) {
    Deque<Node> stack = new LinkedList<>();
    stack.push(node);
    System.out.println();
    while (stack.isEmpty() == false) {
      Node peek = stack.peek();
      if (!peek.sd) {
        peek.sd = true;
        System.out.print(peek.val + " ");
      } else if (!peek.ld && peek.left != null) {
        peek.ld = true;
        stack.push(peek.left);
      } else if (!peek.rd && peek.right != null) {
        peek.rd = true;
        stack.push(peek.right);
      } else {
        stack.pop();
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Node root = new Node(3);
    Node left = new Node(4);
    Node right = new Node(5);
    Node left1 = new Node(6);
    Node right1 = new Node(7);
    Node right2 = new Node(8);

    left.left = left1;
    root.left = left;
    root.right = right;
    right.right = right1;
    right1.right = right2;

    traverseInOrder(root);
  }
}
