import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class StackBasics {
  public static int[] fillNextGreater(int[] input) {
    int[] answer = new int[input.length];
    // Arrays.fill(answer, input.length);
    Deque<Integer> stack = new LinkedList<>();
    int current = 0;
    stack.push(current++);
    while (current < input.length) {
      while (!stack.isEmpty() && input[stack.peek()] < input[current])
        answer[stack.pop()] = current;
      stack.push(current++);
    }

    while (!stack.isEmpty()) answer[stack.pop()] = input.length;

    return answer;
  }

  public static void main(String[] args) {
    int input[] = {2, -1, 7, 8, 5, 9, 12, 13, 7, 18, 1};
    Arrays.stream(fillNextGreater(input)).forEach(e -> System.out.print(e + " "));
    System.out.println();
  }
}
