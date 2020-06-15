public class L0725_LL {
  public ListNode[] splitListToParts(ListNode root, int k) {
    ListNode[] answer = new ListNode[k];
    ListNode temp = root;
    int len = 0;
    while (temp != null) {
      temp = temp.next;
      len++;
    }

    if (k == len || k > len) {
      int index = 0;
      while (root != null) {
        answer[index++] = root;
        ListNode prev = root.next;
        root.next = null;
        root = prev;
      }
      for (; index < k; index++) answer[index] = null;
      return answer;
    }

    int perBlockItem = len / k;
    int itemRemaining = len % k;

    for (int i = 0; i < k; i++) {
      int perBlock = perBlockItem;
      answer[i] = root;
      ListNode prev = null;
      while (perBlock-- > 0) {
        prev = root;
        root = root.next;
      }
      if (itemRemaining > 0) {
        prev = root;
        root = root.next;
        itemRemaining--;
      }
      prev.next = null;
    }

    return answer;
  }
}
