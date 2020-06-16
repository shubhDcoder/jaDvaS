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
  // O(n) space.. to keep n linkages (hashmap)
  /* public Node copyRandomList(Node head) {
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
  } */

  // O(1) space
  public Node copyRandomList(Node head) {
    if (head == null) return null;

    // pass 1. to create links for next nodes.
    Node tempHead = head;
    while (tempHead != null) {
      Node created = new Node(tempHead.val);
      Node tempNext = tempHead.next;
      tempHead.next = created;
      created.next = tempNext;
      tempHead = tempNext;
    }

    // pass 2. to create links for random node
    tempHead = head;
    while (tempHead != null) {
      if (tempHead.random != null) tempHead.next.random = tempHead.random.next;
      tempHead = tempHead.next.next;
    }

    // pass 3. to recreate original links
    tempHead = head;
    Node cloned = new Node(-1);
    Node dummy = cloned;
    while (tempHead != null) {
      cloned.next = tempHead.next;
      cloned = cloned.next;

      tempHead.next = cloned.next;

      tempHead = tempHead.next;
    }
    return dummy.next;
  }
}
