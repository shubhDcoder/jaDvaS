import java.util.*;

public class GraphUtil implements PrintUtil {
    public static boolean[] mark;
    public static List<List<Edge>> graph = new ArrayList<>();
    
    public static void fillGraph() {
        
        // assign arraylist to default null of graph
        for(int i = 0; i < 17; i++) graph.add(new ArrayList<Edge>());

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
        }
        scanner.close();

        mark = new boolean[graph.size()];
    }

    public static void addEdge(int v, int u, int w) {
        graph.get(v).add(new Edge(u, w));
        graph.get(u).add(new Edge(v, w));
    }

    public static void displayGraph() {
        for(int i = 0; i < graph.size(); i++) {
            System.out.println(i + " -> " + graph.get(i));
        }
    }

    public static void resetMark(boolean[] array) {
        for(int i = 0; i < array.length; i++) array[i] = false;
    }
}