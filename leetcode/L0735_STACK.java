import java.util.*;

public class L0735_STACK {
  public int[] asteroidCollision(int[] asteroids) {
    Deque<Integer> stack = new LinkedList<>();

    for (int number : asteroids) {
      if (!stack.isEmpty() && number < 0) {
        if (stack.peek() > 0 && Math.abs(number) < stack.peek()) continue;
        while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(number) >= stack.peek())
          stack.removeFirst();
      } else stack.push(number);
    }

    int[] answer = new int[stack.size()];
    for (int i = answer.length - 1; i >= 0; i--) answer[i] = stack.removeFirst();
    return answer;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new L0735_STACK().asteroidCollision(new int[] {5, 10, -5})));
  }
}
