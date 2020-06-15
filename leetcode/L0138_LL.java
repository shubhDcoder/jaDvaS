import java.util.*;

// Definition for a Node.
class Node {
  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }
}

public class L0138_LL {
  public Node copyRandomList(Node head) {
    if (head == null) return null;
    Map<Node, Node> map = new HashMap<>();
    Node originalHead = head;
    Node random = new Node(-1);
    while (head != null) {
      Node created = new Node(head.val);
      map.put(head, created);
      random.next = created;
      random = created;
      head = head.next;
    }

    Node currHead = originalHead;
    while (currHead != null) {
      if (currHead.random != null) map.get(currHead).random = map.get(currHead.random);
      currHead = currHead.next;
    }

    return random.next;
  }
}
