import java.util.*;

public class L1171_LL {
  public ListNode removeZeroSumSublists(ListNode head) {
    Map<Integer, ListNode> prefixSum = new HashMap<>();
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    prefixSum.put(0, dummy);

    int sum = 0;
    while (head != null) {
      sum = sum + head.val;
      if (prefixSum.containsKey(sum)) {
        ListNode parent = prefixSum.get(sum).next;
        int tempSum = sum + parent.val;
        while (parent != head) {
          ListNode saved = prefixSum.get(tempSum).next;
          prefixSum.get(tempSum).next = null;
          prefixSum.remove(tempSum);
          parent = saved;
          tempSum += parent.val;
        }
        prefixSum.get(sum).next = head.next;
        ListNode delete = head;
        head = head.next;
        delete.next = null;
      } else {
        prefixSum.put(sum, head);
        head = head.next;
      }
    }

    return prefixSum.get(0).next;
  }
}
