public class BSTFunctions {
  static class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
      this.val = val;
    }
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

  static int index;

  public static TreeNode constructTree(int[] arr) {
    if (arr[index] == -1) {
      index++;
      return null;
    }

    TreeNode root = new TreeNode(arr[index++]);
    root.left = constructTree(arr);
    root.right = constructTree(arr);
    return root;
  }

  public static int getHeight(TreeNode node) {
    if (node == null) return -1;
    return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
  }

  public static int getDiameter(TreeNode node) {
    if (node == null) return 0;

    int leftDiameter = getDiameter(node.left);
    int rightDiameter = getDiameter(node.right);

    int leftHeight = getHeight(node.left);
    int rightHeight = getHeight(node.right);

    return Math.max(Math.max(leftDiameter, rightDiameter), leftHeight + rightHeight + 2);
  }

  static int diameter;

  public static int getDiameter_01(TreeNode node) {
    if (node == null) return -1;

    int leftHeight = getDiameter_01(node.left);
    int rightHeight = getDiameter_01(node.right);

    diameter = Math.max(diameter, leftHeight + rightHeight + 2);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  public static void main(String args[]) {
    int[] tree = {
      10, 9, 8, 7, -1, -1, 6, 5, 4, -1, -1, -1, 3, -1, -1, 2, -1, 1, 20, -1, 21, 22, -1, -1, -1, 23,
      -1, 24, -1, -1, 25, -1, -1
    };
    int[] tree1 = {1, 2, 3, -1, -1, -1, -1};

    TreeNode node = constructTree(tree);
    index = 0;
    TreeNode node1 = constructTree(tree1);
    // display(node);

    System.out.println("Diameter of tree0 - " + getDiameter(node));

    System.out.println("Diameter of tree1 - " + getDiameter(node1));

    int ans1 = getDiameter_01(node);
    System.out.println("D of tree0 - " + diameter + ", H of tree0 - " + ans1);
    diameter = 0;
    int ans2 = getDiameter_01(node1);
    System.out.println("D of tree1 - " + diameter + ", H of tree1 - " + ans2);
  }
}
