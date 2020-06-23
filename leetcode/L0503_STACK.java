import java.util.*;

public class L0503_STACK {
  public int[] nextGreaterElements(int[] nums) {
    if (nums == null || nums.length == 0) return null;

    int[] answer = new int[nums.length];
    Deque<Integer> stack = new LinkedList<>();
    int index = 0;
    stack.push(index++);

    while (index < nums.length) {
      while (!stack.isEmpty() && nums[stack.peek()] < nums[index])
        answer[stack.pop()] = nums[index];
      stack.push(index++);
    }

    if (!stack.isEmpty()) {
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] > stack.peek()) {
          while (!stack.isEmpty() && stack.peek() < nums[i]) answer[stack.pop()] = nums[i];
        }
      }
    }

    while (!stack.isEmpty()) answer[stack.pop()] = -1;

    return answer;
  }
}
