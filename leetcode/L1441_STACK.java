import java.util.*;

public class L1441_STACK {
  public List<String> buildArray(int[] target, int n) {
    LinkedList<String> answer = new LinkedList<>();
    int targetIndex = target.length - 1;
    int lastElement = target[targetIndex];

    for (int last = lastElement; last >= 1; last--) {
      if (target[targetIndex] == last) {
        answer.addFirst("Push");
        targetIndex--;
      } else {
        answer.addFirst("Pop");
        answer.addFirst("Push");
      }
    }
    return answer;
  }
}
