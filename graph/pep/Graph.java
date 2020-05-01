import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Graph {
    public static int totalNodes;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static boolean mark[];
    
    public static void constructGraph() {
        Scanner scanner = new Scanner(System.in);
        totalNodes = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < totalNodes; i++) graph.add(new ArrayList<Integer>());
        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        scanner.close();
        mark = new boolean[totalNodes];
    }

    public static void addEdge(int v, int u) {
        graph.get(v).add(u);
    }

    public static void displayGraph() {
        for(int i = 0; i < totalNodes; i++) {
            System.out.println(i + " -> " + graph.get(i) + ", ");
        }
    }

    public static void resetMark(boolean[] array) {
        for(int i = 0; i < array.length; i++) array[i] = false;
    }
}