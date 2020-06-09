import java.util.*;

public class L1466_TREE {

  /*   static class Edge {
    int to;
    boolean isForward;

    public Edge(int to) {
      this.to = to;
    }

    public Edge(int to, boolean isForward) {
      this.to = to;
      this.isForward = isForward;
    }
  }

  List<List<Edge>> graph;
  int answer = 0;
  boolean visited[];

  public int minReorder(int n, int[][] connections) {
    graph = new ArrayList<>(n);
    visited = new boolean[n];
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    for (int[] road : connections) addEdge(road[0], road[1]);
    callDfs(0);
    return answer;
  }

  public void callDfs(int source) {
    visited[source] = true;
    for (Edge edge : graph.get(source)) {
      if (visited[edge.to] == false) {
        if (edge.isForward) answer++;
        callDfs(edge.to);
      }
    }
  }

  public void addEdge(int from, int to) {
    graph.get(from).add(new Edge(to, true));
    graph.get(to).add(new Edge(from));
  } */

  public static int dfs(
      List<List<Integer>> g, int curr, boolean[] visited, Set<Integer> pointToZero) {
    visited[curr] = true;
    List<Integer> adj = g.get(curr);
    int ans = 0;
    for (int i = 0; i < adj.size(); i++) {
      if (!visited[adj.get(i)] && !pointToZero.contains(adj.get(i))) {
        ans += 1 + dfs(g, adj.get(i), visited, pointToZero);
      }
    }
    return ans;
  }

  public int minReorder(int n, int[][] connections) {
    List<List<Integer>> g = new ArrayList<>();
    Set<Integer> pointToZero = new HashSet<>();
    for (int i = 0; i < n; i++) g.add(new ArrayList<>());
    int m = connections.length;
    for (int i = 0; i < m; i++) {
      int sv = connections[i][0], ev = connections[i][1];
      g.get(sv).add(ev);
      if (ev == 0) pointToZero.add(sv);
    }
    boolean[] visited = new boolean[n];
    int ans = 0;
    for (int i = 0; i < n; i++) if (!visited[i]) ans += dfs(g, i, visited, pointToZero);
    return ans;
  }

  public static void main(String[] args) {
    int n = 6;
    int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
    System.out.println(new L1466_TREE().minReorder(n, connections));
  }
}
