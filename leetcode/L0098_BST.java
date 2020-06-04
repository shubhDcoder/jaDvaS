public class L0098_BST {

  static class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return String.valueOf(data);
    }
  }

  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }

  public boolean isValidBST(TreeNode root, int upper_bound, int lower_bound) {
    if (root == null) return true;
    if (root.data < lower_bound || root.data > upper_bound) return false;
    return isValidBST(root.left, root.data, lower_bound)
        && isValidBST(root.right, upper_bound, root.data);
  }
}
