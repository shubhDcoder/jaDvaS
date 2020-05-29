public class BinaryST {
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

  public static TreeNode constructTree(int[] array, int start, int end) {
    if (start > end) return null;

    int mid = start + ((end - start) >> 1);
    TreeNode root = new TreeNode(array[mid]);
    root.left = constructTree(array, start, mid - 1);
    root.right = constructTree(array, mid + 1, end);

    return root;
  }

  public static int getHeight(TreeNode root) {
    if (root == null) return -1;
    return 1 + Math.max(getHeight(root.left), getHeight(root.right));
  }

  public static int getSize(TreeNode root) {
    if (root == null) return 0;
    return 1 + getSize(root.left) + getSize(root.right);
  }

  public static boolean find(TreeNode root, int key) {
    while (root != null) {
      if (root.data == key) return true;
      if (root.data < key) root = root.right;
      else root = root.left;
    }
    return false;
  }

  public static int minElement(TreeNode root) {
    TreeNode prev = null;
    while (root != null) {
      prev = root;
      root = root.left;
    }
    return prev == null ? -1 : prev.data;
  }

  public static int maxElement(TreeNode root) {
    TreeNode prev = null;
    while (root != null) {
      prev = root;
      root = root.right;
    }
    return prev == null ? -1 : prev.data;
  }

  // LOWEST COMMON ANCESTOR OF BST
  public static int findLCA(TreeNode root, int a, int b) {
    if (a > b) {
      int c = a;
      a = b;
      b = c;
    }

    while (root != null) {
      if (b < root.data) root = root.left;
      else if (a > root.data) root = root.right;
      else return find(root, a) && find(root, b) ? root.data : -1;
    }
    return -1;
  }

  // PRODUCE A COLLECTION OF ELEMENTS LIENG WITHIN PROVIDED RANGE
  public static void printElementsInRange(TreeNode root, int start, int end) { // start < end
    if (root == null) return;
    if (start <= root.data) printElementsInRange(root.left, start, end);
    if (root.data <= end && root.data >= start) System.out.print(root.data + " ");
    if (end >= root.data) printElementsInRange(root.right, start, end);
  }

  // INORDER TRAVERSAL OF BINARY SEARCH TREE IS ALWAYS SORTED
  public static void inOrderTraversal(TreeNode root) {
    if (root == null) return;
    inOrderTraversal(root.left);
    System.out.print(root.data + " ");
    inOrderTraversal(root.right);
  }

  // INORDER SUCCESSOR and INORDER PREDECESSOR
  public static void inOrderSuccessorAndPredecessor(TreeNode root, int key) {
    while (root != null) {
      if (root.data == key) break;
      else if (root.data > key) root = root.left;
      else root = root.right;
    }

    if (root == null) return;

    TreeNode tempRight = root.right;
    while (tempRight != null)
      if (tempRight.left != null) tempRight = tempRight.left;
      else break;

    int successor = tempRight == null ? -1 : tempRight.data;

    System.out.println("Successor of " + key + " is > " + successor);

    TreeNode tempLeft = root.left;
    while (tempLeft != null)
      if (tempLeft.right != null) tempLeft = tempLeft.right;
      else break;

    int predecessor = tempLeft == null ? -1 : tempLeft.data;

    System.out.println("Predecessor of " + key + " is > " + predecessor);
  }

  public static void display(TreeNode node) {
    if (node == null) return;

    String s1, s2, s3 = "";
    s1 = (node.left != null) ? String.format("%-5d", node.left.data) : String.format("%-5s", " ");
    s2 = String.format("%-4s%-3d%-4s", "<-", node.data, "->");
    s3 = (node.right != null) ? String.format("%-5d", node.right.data) : String.format("%-5s", " ");

    System.out.println(s1 + s2 + s3);
    display(node.left);
    display(node.right);
  }

  public static void main(String args[]) {
    int[] tree = {1, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
    TreeNode root = constructTree(tree, 0, tree.length - 1);
    System.out.println("tree looks like ------------");
    // display(root);
    // System.out.println();
    System.out.println("height of tree > " + getHeight(root));
    System.out.println("size of tree > " + getSize(root));
    System.out.println("max of tree > " + maxElement(root));
    System.out.println("min of tree > " + minElement(root));
    System.out.println("elements within range (30, 90) are > ");
    printElementsInRange(root, 30, 90);
    System.out.println();
    System.out.println("LCA of 20, 35 is > " + findLCA(root, 20, 35));
    System.out.println("inOrderTraversal is sorted > ");
    inOrderTraversal(root);
    System.out.println();
    inOrderSuccessorAndPredecessor(root, 95);
  }
}
