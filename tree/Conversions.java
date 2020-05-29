public class Conversions {

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

  static TreeNode head;
  static TreeNode tail;

  public static void convertToDoublyLinkedList(TreeNode root) {
    if (root == null) return;
    convertToDoublyLinkedList(root.left);
    if (head == null) head = root;
    else {
      tail.right = root;
      root.left = tail;
    }
    tail = root;
    convertToDoublyLinkedList(root.right);
  }

  public static void printBST2DLL() {
    while (head != null) {
      System.out.print(head.data + "  ");
      head = head.right;
    }
    System.out.println();
  }

  public static void main(String args[]) {
    int[] tree = {
      10, 20, 3, 1, -1, -1, 5, -1, -1, 21, -1, 24, -1, -1, 40, 35, -1, -1, 55, 50, -1, -1, 80, -1,
      -1
    };
    TreeNode root = constructGraph(tree);
    System.out.println("graph looks like");
    display(root);
    convertToDoublyLinkedList(root);
    System.out.println("inOrder DLL converted tree");
    printBST2DLL();
  }

  static int idx = 0;

  public static TreeNode constructGraph(int[] arr) {
    if (idx == arr.length || arr[idx] == -1) {
      idx++;
      return null;
    }

    TreeNode root = new TreeNode(arr[idx++]);
    root.left = constructGraph(arr);
    root.right = constructGraph(arr);

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
}
