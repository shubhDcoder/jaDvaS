import java.util.*;

public class L0496_STACK {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    if (nums1.length == 0 || nums2.length == 0) return new int[0];
    Map<Integer, Integer> map = new HashMap<>();
    int[] answer = new int[nums1.length];

    // prepare next greater element map
    Deque<Integer> stack = new LinkedList<>();
    stack.push(nums2[0]);
    for (int i = 1; i < nums2.length; i++) {
      while (!stack.isEmpty() && stack.peek() < nums2[i]) map.put(stack.pop(), nums2[i]);
      stack.push(nums2[i]);
    }

    // while (!stack.isEmpty()) map.put(stack.pop(), -1);

    for (int i = 0; i < nums1.length; i++) answer[i] = map.getOrDefault(nums1[i], -1);

    return answer;
  }
}
