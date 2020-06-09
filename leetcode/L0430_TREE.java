public class L0430_TREE {

  // Definition for a Node.
  class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
  }

  public Node flatten(Node head) {
    if (head == null) return head;
    callDFS(head);
    return head;
  }

  public Node callDFS(Node node) {
    if (node.child == null && node.next == null) return node;

    if (node.child != null) {
      Node tail = callDFS(node.child);
      Node saveRight = node.next;
      node.child.prev = node;
      node.next = node.child;
      node.child = null;
      if (saveRight == null) return tail;
      tail.next = saveRight;
      saveRight.prev = tail;
      node = saveRight;
    }

    if (node.next != null) return callDFS(node.next);
    else return node;
  }
}
