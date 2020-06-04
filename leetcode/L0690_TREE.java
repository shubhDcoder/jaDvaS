import java.util.*;

public class L0690_TREE {
  class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
  }

  public int getImportance(List<Employee> employees, int id) {
    Map<Integer, Employee> map = new HashMap<>();
    for (Employee emp : employees) map.put(emp.id, emp);

    Deque<Integer> queue = new LinkedList<>();
    queue.offer(id);

    int ans = 0;
    while (queue.isEmpty() == false) {
      int size = queue.size();
      while (size-- > 0) {
        Employee emp = map.get(queue.poll());
        ans += emp.importance;
        queue.addAll(emp.subordinates);
      }
    }
    return ans;
  }
}
