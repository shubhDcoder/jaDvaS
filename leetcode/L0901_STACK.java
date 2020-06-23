import java.util.*;

public class L0901_STACK {

  class Pair {
    int index, price;

    public Pair(int index, int price) {
      this.index = index;
      this.price = price;
    }
  }

  Deque<Pair> stack = new LinkedList<>();
  int index = 0;

  public L0901_STACK() {
    stack.push(new Pair(0, -1));
  }

  public int next(int price) {
    while (stack.peek().index != 0 && stack.peek().price <= price) stack.pop();
    int till = stack.peek().index;
    stack.push(new Pair(++index, price));
    return index - till;
  }

  /**
   * Your StockSpanner object will be instantiated and called as such: StockSpanner obj = new
   * StockSpanner(); int param_1 = obj.next(price);
   */
}
