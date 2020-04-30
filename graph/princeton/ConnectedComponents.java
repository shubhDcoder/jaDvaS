public class ConnectedComponents {

    int count;
    int componentId[];
    boolean visited[];
    
    public ConnectedComponents(Graph graph, int source) {
        componentId = new int[graph.tVertex];
        visited = new boolean[graph.tVertex];

        for(int v = 0; v < graph.tVertex; v++) {
            if(visited[v] == false) {
                getConnectedComponents(graph, v);
                count++;
            }
        }
    }

    public void getConnectedComponents(Graph graph, int source) {
        visited[source] = true;
        componentId[source] = count;

        for(int adj : graph.adj(source)) {
            if(visited[adj] == false) {
                getConnectedComponents(graph, adj);        
            }
        }
    }

    public int getCount() {return count;}

    public boolean isConnected(int source, int dest) {
        return componentId[source] == componentId[dest];
    }
}