// add, get, remove
public class LLBasic {
  public static void main(String[] args) {
    SLL sll = new SLL();
    for (int i = 0; i < 10; i++) sll.addLast(i);
    sll.display();
    for (int i = 0; i < 5; i++) sll.removeFirst();
    sll.display();
    for (int i = 0; i < 10; i++) sll.removeFirst();
    sll.display();
  }
}

class SLL {
  private Node head, tail;
  private int size;

  public void display() {
    Node temp = head;
    StringBuilder answer = new StringBuilder("size : " + size + " : ");
    while (temp != null) {
      answer.append(temp + " ");
      temp = temp.next;
    }
    System.out.println(answer.toString());
  }

  // Add methods

  public Node addAt(int index, int key) {
    if (index < 0 || index >= size) {
      System.out.println("can not add, check index!");
      return null;
    }
    return addNodeAtIndex(index, key);
  }

  private Node addNodeAtIndex(int index, int key) {
    size++;
    if (index == 0) return addFirst(key);
    else if (index == size) return addLast(key);
    else {
      Node prev = getNodeAtIndex(index - 1);
      Node toBeAdded = new Node(key);
      toBeAdded.next = prev.next;
      prev.next = toBeAdded;
      return head;
    }
  }

  public Node addLast(int key) {
    return addLastNode(new Node(key));
  }

  private Node addLastNode(Node node) {
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      tail = node;
    }
    size++;
    return head;
  }

  public Node addFirst(int key) {
    return addFirstNode(new Node(key));
  }

  private Node addFirstNode(Node node) {
    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.next = head;
      head = node;
    }
    size++;
    return head;
  }

  // Remove methods
  public Node removeLast() {
    if (size == 0) {
      System.out.println("size is 0, can't remove!");
      return null;
    }
    return removeLastNode();
  }

  private Node removeLastNode() {
    Node ret = tail;
    if (size == 1) {
      head = null;
      tail = null;
    } else {
      Node temp = getNodeAtIndex(size - 2);
      temp.next = null;
      tail = temp;
    }
    size--;
    return ret;
  }

  public Node removeFirst() {
    if (size == 0) {
      System.out.println("size is 0, can't remove!");
      return null;
    }
    return removeFirstNode();
  }

  private Node removeFirstNode() {
    Node ret = head;
    if (size == 1) {
      head = null;
      tail = null;
    } else {
      head = head.next;
    }
    size--;
    return ret;
  }

  // Get Methods

  public Node getHead() {
    if (size == 0) {
      System.out.println("no element found!");
      return null;
    }
    return head;
  }

  public Node getTail() {
    if (size == 0) {
      System.out.println("no element found!");
      return null;
    }
    return tail;
  }

  public Node getAt(int index) {
    if (size < 0 || index >= size) {
      System.out.println("can not remove. check index!");
      return null;
    }
    return getNodeAtIndex(index);
  }

  private Node getNodeAtIndex(int index) {
    Node temp = head;
    while (index-- > 0) temp = temp.next;
    return temp;
  }
}

class Node {
  Node next;
  int data;

  public Node(int data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return String.valueOf(this.data);
  }
}
