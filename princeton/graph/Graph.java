import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Graph {
    public int tVertex;
    public int tEdge;
    public List<List<Integer>> adjacencyList;

    public Graph(int vertex) {
        this.tVertex = vertex;
        adjacencyList = new ArrayList<>(vertex);
        for(int i = 0; i < tVertex; i++) adjacencyList.add(new ArrayList<Integer>());
    }

    public Graph(Scanner scan) {
        this(scan.nextInt());
        this.tEdge = scan.nextInt();
        for(int line = 0; line < tEdge; line++) {
            int v = scan.nextInt();
            int w = scan.nextInt();
            addEdge(v, w);
        }
    }

    public int tVertex() {
        return tVertex;
    }

    public int tEdge() {
        return tEdge;
    }

    public List<Integer> adj(int vertex) {
        return adjacencyList.get(vertex);
    }

    public void addEdge(int v, int w) {
        adjacencyList.get(v).add(w);
        adjacencyList.get(w).add(v);
    }

    public static int degree(Graph graph, int vertex) {
        return graph.adj(vertex).size();
    }

    public static int maxDegree(Graph graph) {
        int degree = Integer.MIN_VALUE;
        for(int v = 0; v < graph.tVertex(); v++) {
            int tdegree = degree(graph, v);
            if(tdegree > degree) degree = tdegree;
        }
        return degree;
    }

    public static int avgDegree(Graph graph) {
        return (2 * graph.tEdge()) / graph.tVertex();
    }

    public static int countSelfLoop(Graph graph) {
        int count = 0;
        for(int v = 0; v < graph.tVertex; v++) {
            for(int adj : graph.adj(v)) {
                if(adj == v) count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("vertices : " + this.tVertex + "\t|\tEdges : " + this.tEdge);
        sb.append("\n" + String.format("%50s", " ").replace(" ", "=") + "\n");
        sb.append("source\tdestination");
        sb.append("\n" + String.format("%50s", " ").replace(" ", "=") + "\n");
        for(int v = 0; v < this.tVertex; v++) {
            sb.append(v + "\t|\t");
            for(int adj : this.adjacencyList.get(v)) sb.append(adj + " ");
            sb.append("\n");
        }
        return sb.toString();
    }
}