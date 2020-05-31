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

  public static final int MIN_V = Integer.MIN_VALUE;
  public static final int MAX_V = Integer.MAX_VALUE;

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

  public static TreeNode find(TreeNode root, int key) {
    while (root != null) {
      if (root.data == key) return root;
      if (root.data < key) root = root.right;
      else root = root.left;
    }
    return null;
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
      else return find(root, a) != null && find(root, b) != null ? root.data : -1;
    }
    return -1;
  }

  // PRODUCE A COLLECTION OF ELEMENTS LIENG WITHIN PROVIDED RANGE
  public static void printElementsInRange(TreeNode root, int start, int end) { // start < end
    if (root == null) return;
    if (start <= root.data) printElementsInRange(root.left, start, end);
    // if (root.data <= end && root.data >= start)
    System.out.print(root.data + " ");
    if (end >= root.data) printElementsInRange(root.right, start, end);
  }

  // PRODUCE A COLLECTION OF ELEMENTS LIENG WITHIN PROVIDED RANGE - Version 2
  // public static void printElementsInRange_v2(TreeNode root, int start, int end) {
  //   if(root == null) return;
  //   printElementsInRange_v2()
  // }

  // INORDER TRAVERSAL OF BINARY SEARCH TREE IS ALWAYS SORTED
  public static void inOrderTraversal(TreeNode root) {
    if (root == null) return;
    inOrderTraversal(root.left);
    System.out.print(root.data + " ");
    inOrderTraversal(root.right);
  }

  // INORDER SUCCESSOR and INORDER PREDECESSOR
  public static void inOrderSuccessorAndPredecessor(TreeNode root, int key) {
    TreeNode predecessor = null;
    TreeNode successor = null;
    while (root != null) {
      if (root.data == key) break;
      if (root.data > key) {
        successor = root;
        root = root.left;
      } else {
        predecessor = root;
        root = root.right;
      }
    }

    if (root == null) return;

    TreeNode tempRight = root.right;
    while (tempRight != null)
      if (tempRight.left != null) tempRight = tempRight.left;
      else break;

    successor = tempRight == null ? successor : tempRight;

    syso("Successor of " + key + " is > " + successor);

    TreeNode tempLeft = root.left;
    while (tempLeft != null)
      if (tempLeft.right != null) tempLeft = tempLeft.right;
      else break;

    predecessor = tempLeft == null ? predecessor : tempLeft;

    syso("Predecessor of " + key + " is > " + predecessor);
  }

  // CONSTRUCT BST FROM PREORDER TRAVERSAL ARRAY

  static int preIndex = 0;

  public static TreeNode constructTreeFromPreOrder(int[] input, int min, int max) {
    if (preIndex == input.length - 1) return new TreeNode(input[preIndex]);
    int current = input[preIndex];
    TreeNode root = new TreeNode(current);
    if (preIndex < input.length - 1)
      if (input[preIndex + 1] > min && input[preIndex + 1] < current) {
        preIndex++;
        root.left = constructTreeFromPreOrder(input, min, current);
      }
    if (preIndex < input.length - 1)
      if (input[preIndex + 1] > current && input[preIndex + 1] < max) {
        preIndex++;
        root.right = constructTreeFromPreOrder(input, current, max);
      }
    return root;
  }

  static int preIndex1 = 0;

  public static TreeNode constructTreeFromPreOrder_v2(int[] input, int min, int max) {
    if (preIndex1 == input.length || input[preIndex1] < min || input[preIndex1] > max) return null;
    TreeNode root = new TreeNode(input[preIndex1++]);
    if (preIndex1 < input.length) root.left = constructTreeFromPreOrder_v2(input, min, root.data);
    if (preIndex1 < input.length) root.right = constructTreeFromPreOrder_v2(input, root.data, max);
    return root;
  }

  // GET HEIGHT OF BINARY SEARCH TREE WITHOUT CREATING BST

  static int heightIndex = 0;

  public static int getHeightWithoutCreating(int[] input, int min, int max) {
    if (heightIndex == input.length || input[heightIndex] < min || input[heightIndex] > max)
      return -1;
    int leftHeight = -1;
    int rightHeight = -1;
    int me = input[heightIndex++];
    if (heightIndex < input.length) leftHeight = getHeightWithoutCreating(input, min, me);
    if (heightIndex < input.length) rightHeight = getHeightWithoutCreating(input, me, max);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  // ADD NODE IN BST

  public static TreeNode addNode(TreeNode root, int key) {
    TreeNode prev = null;
    TreeNode parent = root;
    while (root != null) {
      prev = root;
      if (key < root.data) root = root.left;
      else if (key > root.data) root = root.right;
    }
    TreeNode toBeAdded = new TreeNode(key);
    if (prev == null) parent = toBeAdded;
    else if (key < prev.data) prev.left = toBeAdded;
    else prev.right = toBeAdded;

    return parent;
  }

  // DELETE NODE FROM BST

  public static TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    else if (key < root.data) root.left = deleteNode(root.left, key);
    else if (key > root.data) root.right = deleteNode(root.right, key);
    else {
      if (root.left == null || root.right == null)
        return root.left == null ? root.right : root.left;
      else {
        root.left = deleteMaxNode(root.left, root);
      }
    }
    return root;
  }

  public static TreeNode deleteMaxNode(TreeNode root, TreeNode parent) {
    if (root.right != null) root.right = deleteMaxNode(root.right, parent);
    else {
      parent.data = root.data;
      return root.left != null ? root.left : null;
    }
    return root;
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
    int[] tree = {5, 15, 20, 25, 30, 35, 40, 50, 55, 60, 65, 75, 80, 85, 90};
    TreeNode root = constructTree(tree, 0, tree.length - 1);
    markHeading("Binary Search tree");
    printLine("tree looks like");
    display(root);
    printLine("Different functinos of tree");
    syso("height of tree > " + getHeight(root));
    syso("size of tree > " + getSize(root));
    syso("max of tree > " + maxElement(root));
    syso("min of tree > " + minElement(root));
    printLine("elements within range (30, 90) are");
    printElementsInRange(root, 30, 90);
    printNewLine();
    printLine("LCA");
    syso("LCA of 20, 35 is > " + findLCA(root, 20, 35));
    printLine("inOrderTraversal is sorted > ");
    inOrderTraversal(root);
    TreeNode node55 = find(root, 55);
    TreeNode node54 = new TreeNode(54);
    TreeNode node53 = new TreeNode(53);
    node55.left = node54;
    node54.left = node53;
    printLine("INorder Successor and predecessor are ");
    inOrderSuccessorAndPredecessor(root, 55);
    printNewLine();
    int[] preOrderArray = {50, 17, 9, 14, 12, 23, 19, 76, 54, 72, 67};
    // TreeNode preRoot = constructTreeFromPreOrder(preOrderArray, MIN_V, MAX_V);
    TreeNode preRoot = constructTreeFromPreOrder_v2(preOrderArray, MIN_V, MAX_V);
    addNode(preRoot, 20);
    addNode(preRoot, 55);
    addNode(preRoot, 25);
    printLine("construct BST from preOrderTraversal array tree looks like");
    display(preRoot);
    printLine("get height without constructing tree");
    syso("height is " + getHeightWithoutCreating(preOrderArray, MIN_V, MAX_V));
  }

  // random utility methods
  public static final int FIXED_LEN = 135;

  public static void printLine() {
    System.out.println(String.format("%140s", " ").replaceAll(" ", "*"));
  }

  public static void printLine(String word) {
    printNewLine();
    int write = word.length();
    int fill = (FIXED_LEN - write) / 2;
    System.out.println(
        String.format("%" + fill + "s", " ").replaceAll(" ", "*")
            + " : "
            + word
            + " : "
            + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "*"));
  }

  public static void printNewLine() {
    System.out.println();
  }

  public static void syso(Object obj) {
    System.out.println(obj);
  }

  public static void markHeading(String string) {
    printNewLine();
    printLine();
    syso(string);
    printLine();
    printNewLine();
  }
}
