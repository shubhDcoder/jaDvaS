public class MaxPathSumBwLeaves {
  public static void main(String args[]) {
    Node tree = new Node();
    tree = new Node(-15);
    tree.left = new Node(5);
    tree.right = new Node(6);
    tree.left.left = new Node(-8);
    tree.left.right = new Node(1);
    tree.left.left.left = new Node(2);
    tree.left.left.right = new Node(6);
    tree.right.left = new Node(3);
    tree.right.right = new Node(9);
    tree.right.right.right = new Node(0);
    tree.right.right.right.left = new Node(4);
    tree.right.right.right.right = new Node(-1);
    tree.right.right.right.right.left = new Node(10);

    getMaxPathSum(tree);
    System.out.println(maxSum);
  }

  static int maxSum = Integer.MIN_VALUE;

  public static int getMaxPathSum(Node root) {
    if (root == null) return Integer.MIN_VALUE;
    if (root.left == null && root.right == null) {
      return root.val;
    }

    int leftBest = getMaxPathSum(root.left);
    int rightBest = getMaxPathSum(root.right);
    maxSum = Math.max(leftBest + rightBest + root.val, maxSum);
    return Math.max(leftBest, rightBest) + root.val;
  }

  static class Node {
    int val;
    Node left;
    Node right;

    public Node() {}

    public Node(int val) {
      this.val = val;
    }
  }
}
