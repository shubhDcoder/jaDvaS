import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L1442_TREE {
  List<List<Integer>> graph;
  byte[] visited;
  int answer;

  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    graph = new ArrayList<>(n);
    visited = new byte[n];
    for (int i = 0; i < n; i++) graph.add(new LinkedList<>());
    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    callDFS(0, hasApple);
    return (answer << 1);
  }

  public boolean callDFS(int source, List<Boolean> hasApple) {
    visited[source] = 1;
    boolean included = false;
    for (int edge : graph.get(source)) {
      if (visited[edge] == 0) {
        boolean ret = callDFS(edge, hasApple);
        if (ret) answer++;
        included = ret || included;
      }
    }

    if (included == false && hasApple.get(source)) return true;
    return included;
  }

  /*

   List<List<Integer>> graph;
   byte[] visited;

   public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
     graph = new ArrayList<>(n);
     visited = new byte[n];
     for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
     for (int[] edge : edges) {
         graph.get(edge[0]).add(edge[1]);
         graph.get(edge[1]).add(edge[0]);
     }
     int result = callDFS(0, hasApple);
     return result == 0 ? result : (2 * (result - 1));
   }

   public int callDFS(int source, List<Boolean> hasApple) {
     visited[source] = 1;
     int myCount = 0;
     for (int edge : graph.get(source)) {
       if (visited[edge] == 0) myCount += callDFS(edge, hasApple);
     }
     return myCount > 0 ? myCount + 1 : (hasApple.get(source) ? 1 : 0);
   }

  */
}
