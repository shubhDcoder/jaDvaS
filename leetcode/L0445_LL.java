import java.util.*;

public class L0445_LL {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
    Deque<ListNode> stackA = new LinkedList<>();
    Deque<ListNode> stackB = new LinkedList<>();

    ListNode tempHead = new ListNode(-1);
    while (l1.next != null) {
      stackA.push(l1);
      l1 = l1.next;
    }
    while (l2.next != null) {
      stackB.push(l2);
      l2 = l2.next;
    }

    int carry = 0;
    while (!stackA.isEmpty() || !stackB.isEmpty() || carry > 1) {
      int a = stackA.isEmpty() ? 0 : stackA.pop().val;
      int b = stackB.isEmpty() ? 0 : stackB.pop().val;
      int result = a + b + carry;
      carry = result / 10;
      int store = result % 10;
      ListNode node = new ListNode(store);
      node.next = tempHead.next;
      tempHead.next = node;
    }

    return tempHead.next;
  }

  public static void main(String[] args) {
    int[] arr1 = {7, 2, 4, 3};
    int[] arr2 = {5, 6, 4};
    ListNode head = new ListNode(arr1[0]);
    ListNode temp = head;
    for (int i = 1; i < arr1.length; i++) {
      temp.next = new ListNode(arr1[i]);
      temp = temp.next;
    }

    ListNode head2 = new ListNode(arr2[0]);
    ListNode temp2 = head;
    for (int i = 1; i < arr2.length; i++) {
      temp2.next = new ListNode(arr2[i]);
      temp2 = temp2.next;
    }
    new L0445_LL().addTwoNumbers(head, head2);
  }
}
