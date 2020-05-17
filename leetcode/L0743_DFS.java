import java.util.*;

public class L0743_DFS {
    public static void main(String args[]) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int N = 4; 
        int K = 2;
        System.out.println(new L0743_DFS().networkDelayTime(times, N, K));
    }

    List<List<Edge>> graph;

    static class Edge {
        int toVertex;
        int weight;
        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    int travelled = 0;
    byte[] visited;
    public int networkDelayTime(int[][] times, int N, int K) {
        visited = new byte[N + 1];
        graph = new ArrayList<>(N + 1);
        for(int i = 1; i <= N + 1; i++) graph.add(new ArrayList<>());
        for(int[] time : times) addEdge(time[0], time[1], time[2]);

        visited[K] = 1;
        travelled++;

        int res = callDfs(new Edge(K, 0), 0);
        if(travelled != N) return -1;
        return res;
    }

    public int callDfs(Edge s, int depth) {
        int tD = 0;
        // System.out.println("came for " + s.toVertex + " " + s.weight + " depth " + depth);
        for(Edge adj : graph.get(s.toVertex)) {
            if(visited[adj.toVertex] == 0) {
                visited[adj.toVertex] = 1;
                travelled++;
                tD = Math.max(tD, callDfs(adj, depth + 1));
            } else return -1;
        }
        // System.out.println("returning with " + s.toVertex + "  " + (tD == 0 ? depth : tD));
        return tD == 0 ? depth : tD;
    }

    public void addEdge(int source, int dest, int weight) {
        graph.get(source).add(new Edge(dest, weight));
    }
}