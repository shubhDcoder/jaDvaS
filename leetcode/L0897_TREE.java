public class L0897_TREE {
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

  TreeNode head, tail;

  public TreeNode increasingBST(TreeNode root) {
    increasingBST_(root);
    return head;
  }

  public void increasingBST_(TreeNode root) {
    if (root == null) return;
    increasingBST_(root.left);
    if (head == null) head = root;
    else {
      tail.right = root;
      root.left = null;
    }
    tail = root;
    increasingBST_(root.right);
  }
}
