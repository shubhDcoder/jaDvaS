import java.util.Queue;
import java.util.LinkedList;

public class BredthFirstSearchPaths {
    int source;
    int edgeTo[];
    boolean visited[];

    public BredthFirstSearchPaths(Graph graph, int source) {
        this.source = source;
        edgeTo = new int[graph.tVertex];
        visited = new boolean[graph.tVertex];
        calculatePathsUsingBfs(graph, source);
    }

    public void calculatePathsUsingBfs(Graph graph, int source) {
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        edgeTo[source] = source;
        queue.offer(source);
        
        while(queue.size() > 0) {
            int removed = queue.poll();
            for(int adj : graph.adj(removed)) {
                if(!visited[adj]) {
                    visited[adj] = true;
                    queue.offer(adj);
                    edgeTo[adj] = removed;
                }
            }
        }
    }

    public boolean hasPath(int dest) {
        return visited[dest];
    }

    public Iterable<Integer> getPath(int dest) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int v = dest; v != source; v = edgeTo[v]) {
            list.addFirst(v);
        }
        list.addFirst(source);
        return list;
    }
}