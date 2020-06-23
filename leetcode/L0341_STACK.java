// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
import java.util.*;

interface NestedInteger {
  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}

public class L0341_STACK {

  Deque<Integer> queue = new LinkedList<>();

  public L0341_STACK(List<NestedInteger> nestedList) {
    constructQueue(nestedList);
  }

  public void constructQueue(List<NestedInteger> nestedList) {
    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) queue.offerLast(ni.getInteger());
      else constructQueue(ni.getList());
    }
  }

  public Integer next() {
    return queue.removeFirst();
  }

  public boolean hasNext() {
    return !queue.isEmpty();
  }
}

/*
Your NestedIterator object will be instantiated and called as such:
NestedIterator i = new NestedIterator(nestedList);
while (i.hasNext()) v[f()] = i.next();
*/
