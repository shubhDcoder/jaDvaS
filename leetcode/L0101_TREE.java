public class L0101_TREE {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public boolean isSymmetric(TreeNode root) {
    return isSymmetric(root, root);
  }

  public boolean isSymmetric(TreeNode rootA, TreeNode rootB) {
    if (rootA == null && rootB == null) return true;
    if (rootA != null && rootB != null && rootA.val == rootB.val) {
      return isSymmetric(rootA.left, rootB.right) && isSymmetric(rootA.right, rootB.left);
    } else return false;
  }

  public static void main(String[] args) {
    System.out.println("printing");
  }
}
