import java.util.*;

public class L0394_STACK {
  public String decodeString(String s) {
    if (s.length() < 4) return s;
    Deque<Integer> stack = new LinkedList<>();

    Deque<StringBuilder> builders = new LinkedList<>();
    builders.add(new StringBuilder());

    char[] input = s.toCharArray();
    for (int i = 0; i < input.length; i++) {
      int forInt = input[i] - '0';
      if (forInt >= 0 && forInt <= 9) {
        StringBuilder number = new StringBuilder();
        number.append(input[i]);
        while (input[i + 1] - '0' >= 0 && input[i + 1] - '0' <= 9) {
          number.append(input[i + 1]);
          i++;
        }
        stack.addFirst(Integer.parseInt(number.toString()));
        builders.addFirst(new StringBuilder());
        i++;
      } else if (input[i] == ']') {
        int times = stack.removeFirst();
        if (times == 0) {
          builders.removeFirst();
          continue;
        }
        String repeated = builders.peekFirst().toString();
        while (times-- > 1) builders.peekFirst().append(repeated);
        String attach = builders.removeFirst().toString();
        builders.peekFirst().append(attach);
      } else {
        builders.peekFirst().append(input[i]);
      }
    }
    return builders.peekFirst().toString();
  }

  public static void main(String[] args) {
    System.out.println(new L0394_STACK().decodeString("11[a]2[bc]"));
  }
}
