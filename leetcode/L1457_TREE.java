public class L1457_TREE {
  static class TreeNode {
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

  int[] counts = new int[10];
  int answer = 0;

  public int pseudoPalindromicPaths(TreeNode root) {
    if (root == null) return 0;
    callDFS(root, 1);
    return answer;
  }

  public void callDFS(TreeNode root, int level) {
    counts[root.val] += 1;
    if (root.left == null && root.right == null) {
      int odd = 0;
      for (int c : counts) if (c % 2 != 0) odd++;
      if (level % 2 == 0) {
        if (odd == 0) answer += 1;
      } else if (odd == 1) answer += 1;
    }
    if (root.left != null) callDFS(root.left, level + 1);
    if (root.right != null) callDFS(root.right, level + 1);
    counts[root.val] -= 1;
  }
}
