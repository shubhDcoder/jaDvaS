public class L0143_LL {
  public void reorderList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) return;

    ListNode tempHead = head;

    ListNode he = head, she = head;
    while (she.next != null && she.next.next != null) {
      he = he.next;
      she = she.next.next;
    }

    ListNode prev = null;
    ListNode curr = he.next;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    he.next = null;

    ListNode dummy = new ListNode(-1);
    ListNode ret = dummy;
    while (tempHead != null && prev != null) {
      dummy.next = tempHead;
      tempHead = tempHead.next;
      dummy.next.next = prev;
      dummy = prev;
      prev = prev.next;
    }

    if (tempHead != null) dummy.next = tempHead;
    if (prev != null) dummy.next = prev;

    head = ret.next;

    // list in this function is
    System.out.println("after reordering- 1 -");
    tempHead = head;
    while (tempHead.next != null) {
      System.out.print(tempHead.val + " ");
      tempHead = tempHead.next;
    }

    againReorderList(head);
  }

  public void againReorderList(ListNode head) {
    ListNode headB = head.next;
    ListNode pointerA = head;
    ListNode pointerB = head.next;

    while (pointerA.next != null && pointerB.next != null) {
      pointerA.next = pointerB.next;
      pointerA = pointerA.next;
      pointerB.next = pointerA.next;
      pointerB = pointerB.next;
    }

    ListNode prev = null;
    ListNode curr = headB;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    pointerA.next = prev;

    System.out.println("\nagain after reordering- 2 -");
    ListNode tempHead = head;
    while (tempHead.next != null) {
      System.out.print(tempHead.val + " ");
      tempHead = tempHead.next;
    }
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    ListNode head = new ListNode(arr[0]);
    ListNode temp = head;
    for (int i = 1; i < arr.length; i++) {
      temp.next = new ListNode(arr[i]);
      temp = temp.next;
    }
    new L0143_LL().reorderList(head);
  }
}
