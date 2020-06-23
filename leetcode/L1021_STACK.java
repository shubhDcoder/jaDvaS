public class L1021_STACK {
  public String removeOuterParentheses(String S) {
    int count = 0;
    StringBuilder tempAnswer = new StringBuilder();
    for (char c : S.toCharArray()) {
      if (c == '(') {
        if (count != 0) tempAnswer.append(c);
        count++;
      } else {
        count--;
        if (count != 0) tempAnswer.append(c);
      }
    }
    return tempAnswer.toString();
  }

  public static void main(String[] args) {
    System.out.println(new L1021_STACK().removeOuterParentheses("(()())(())"));
  }
}
