public class L0108_TREE {
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

  int index = 0;

  public TreeNode sortedArrayToBST(int[] nums) {
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
    if (start > end) return null;
    if (start == end) return new TreeNode(nums[start]);
    int mid = start + ((end - start) >> 1);
    TreeNode root = new TreeNode(nums[mid]);
    root.left = sortedArrayToBST(nums, start, mid - 1);
    root.right = sortedArrayToBST(nums, mid + 1, end);
    return root;
  }
}
