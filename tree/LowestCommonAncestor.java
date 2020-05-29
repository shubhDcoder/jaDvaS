public class LowestCommonAncestor {

  static class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
      this.val = val;
    }
  }

  private static int index;

  public static TreeNode constructTree(int[] array) {
    if (array[index] == -1 || index >= array.length) {
      index++;
      return null;
    }

    TreeNode root = new TreeNode(array[index++]);
    root.left = constructTree(array);
    root.right = constructTree(array);
    return root;
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

  private static TreeNode lowestAncestor = null;

  public static boolean findLowestCommonAncestor(TreeNode root, int key1, int key2) {
    if (root == null) return false;

    boolean amI = (root.val == key1 || root.val == key2);

    boolean isLeft = findLowestCommonAncestor(root.left, key1, key2);
    if (lowestAncestor != null) return true;
    if (amI && isLeft) {
      lowestAncestor = root;
      return true;
    }

    boolean isRight = findLowestCommonAncestor(root.right, key1, key2);
    if (lowestAncestor != null) return true;
    if ((isRight && amI) || (isRight && isLeft)) {
      lowestAncestor = root;
      return true;
    }

    return amI || isLeft || isRight;
  }

  public static void main(String args[]) {
    int[] tree = {0, 1, -1, 3, -1, -1, 2, 4, -1, -1, 5, -1, -1};
    TreeNode root = constructTree(tree);
    // display(root);
    findLowestCommonAncestor(root, 3, 1);
    System.out.println("ancestor is : " + ((lowestAncestor == null) ? "-1" : lowestAncestor.val));
  }
}
