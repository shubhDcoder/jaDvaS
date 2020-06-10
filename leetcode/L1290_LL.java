public class L1290_LL {

  public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public int getDecimalValue(ListNode head) {
    int answer = 0;
    while (head != null) {
      answer = (answer << 1);
      if (head.val == 1) {
        answer = answer | 1;
      }
      head = head.next;
    }
    return answer;
  }
}
