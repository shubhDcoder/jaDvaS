import java.util.LinkedList;

public class DepthFirstSearchPaths {
    
    public int[] edgeTo;
    public boolean[] mark;
    public int source;

    public DepthFirstSearchPaths(Graph graph, int source) {
        edgeTo = new int[graph.tVertex];
        mark = new boolean[graph.tVertex];
        this.source = source;
        calculateAllPathsUsingDfs(graph, source);
    }

    public void calculateAllPathsUsingDfs(Graph graph, int vertex) {
        mark[vertex] = true;
        for(int adj : graph.adj(vertex)) {
            if(mark[adj] == false) {
                edgeTo[adj] = vertex;
                calculateAllPathsUsingDfs(graph, adj);
            }
        }
    }

    public boolean hasPath(int dest) {
        return mark[dest];
    }

    public Iterable<Integer> getPath(int dest) {
        if(!hasPath(dest)) return null;

        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int v = dest; v != source; v = edgeTo[v]) {
            list.addFirst(v);
        }
        list.addFirst(source);
        return list;
    }
}