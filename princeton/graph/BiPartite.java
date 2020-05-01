import java.util.Queue;
import java.util.LinkedList;

public class BiPartite {

    boolean visited[];
    boolean markedWith[];
    boolean isBipartite;

    public BiPartite(Graph graph) {
        visited = new boolean[graph.tVertex];
        markedWith = new boolean[graph.tVertex];

        callBfs(graph);
    }

    public void callBfs(Graph graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        while(queue.size() > 0) {
            int removed = queue.poll();
            for(int adj : graph.adj(removed)) {
                if(visited[adj] == false) {
                    queue.offer(adj);
                    visited[removed] = true;
                    markedWith[adj] = !markedWith[removed];
                } else if(markedWith[adj] == markedWith[removed]) 
                    isBipartite = true;
            }
        }
    }
    
    public boolean isBipartite() {return isBipartite;}
}