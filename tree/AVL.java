import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AVL {
  static class AVLNode {
    int val, height, balance;
    AVLNode left, right;

    public AVLNode(int val) {
      this.val = val;
    }
  }

  public static void updateNode(AVLNode node) {
    int lh = node.left == null ? -1 : node.left.height;
    int rh = node.right == null ? -1 : node.right.height;
    node.height = Math.max(rh, lh) + 1;
    node.balance = lh - rh;
  }

  public static AVLNode rotateRR(AVLNode node) {
    AVLNode root = node.left;
    AVLNode rootsRight = root.right;
    root.right = node;
    node.left = rootsRight;
    updateNode(node);
    updateNode(root);
    return root;
  }

  public static AVLNode rotateLL(AVLNode node) {
    AVLNode root = node.right;
    AVLNode rootsLeft = root.left;
    root.left = node;
    node.right = rootsLeft;
    updateNode(node);
    updateNode(root);
    return root;
  }

  public static AVLNode rectifyMe(AVLNode node) {
    updateNode(node);
    if (node.balance == 2) {
      int bal = node.left.balance;
      if (bal == 1) {
        node = rotateRR(node);
      } else if (bal == -1) {
        node.left = rotateLL(node.left);
        node = rotateRR(node);
      }
    } else if (node.balance == -2) {
      int bal = node.right.balance;
      if (bal == 1) {
        node.right = rotateRR(node.right);
        node = rotateLL(node);
      } else if (bal == -1) {
        node = rotateLL(node);
      }
    }
    return node;
  }

  public static AVLNode addNode(AVLNode root, int key) {
    if (root == null) return new AVLNode(key);
    if (key < root.val) root.left = addNode(root.left, key);
    else if (key > root.val) root.right = addNode(root.right, key);
    return rectifyMe(root);
  }

  public static AVLNode removeNode(AVLNode root, int key) {
    if (root == null) return null;
    if (key < root.val) root.left = removeNode(root.left, key);
    else if (key > root.val) root.right = removeNode(root.right, key);
    else {
      if (root.left == null || root.right == null)
        return root.left == null ? root.right : root.left;
      else {
        root.left = removeHelper(root.left, root);
        return root;
      }
    }
    return rectifyMe(root);
  }

  private static AVLNode removeHelper(AVLNode child, AVLNode parent) {
    if (child.right != null) child.right = removeHelper(child.right, parent);
    parent.val = child.val;
    return child.left;
  }

  public static AVLNode constructTree(int[] input, int start, int end) {
    if (start > end) return null;
    int mid = start + ((end - start) >> 1);
    AVLNode root = new AVLNode(input[mid]);
    root.left = constructTree(input, start, mid - 1);
    root.right = constructTree(input, mid + 1, end);
    updateNode(root);
    return root;
  }

  public static void display(AVLNode node) {
    if (node == null) return;

    String s1, s2, s3 = "";
    s1 = (node.left != null) ? String.format("%-5d", node.left.val) : String.format("%-5s", " ");
    s2 = String.format("%-4s%-3d[%2d : %2d]%-4s", "<-", node.val, node.balance, node.height, "->");
    s3 = (node.right != null) ? String.format("%-5d", node.right.val) : String.format("%-5s", " ");

    System.out.println(s1 + s2 + s3);
    display(node.left);
    display(node.right);
  }

  public static void main(String[] args) {
    // int[] treeArray = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    // AVLNode root = constructTree(treeArray, 0, treeArray.length - 1);
    // display(root);
    System.out.println();
    // display(root);
    // System.out.println();
    // root = addNode(root, 15);
    AVLNode loopRoot = null;
    Set<Integer> unique = new HashSet<>();
    Random random = new Random();
    for (int i = 0; i < 20; i++) {
      int nmbr = random.nextInt(50);
      if (unique.contains(nmbr)) continue;
      unique.add(nmbr);
      loopRoot = addNode(loopRoot, nmbr);
      display(loopRoot);
      System.out.println("--------------------------------------------");
    }
  }
}
