import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;

public class Bfs {

    // code start for BFS
    public static void main(String args[]) {
        printLine("BFS");
        constructGraph();
        displayGraph();

        callBfs();
        callBfsWithDelimiter();
        callBfsWithClassLevelVar();
        callBfsWithSizeLoop();
        callBfsWithoutDetectingCycle();
    }

    public static void callBfsWithoutDetectingCycle() {
        markHeading("BFS path traversed WITHOUT cycle detection with within size loop");
        resetMark(mark);
        int destination = 5;
        int level = 0;

        LinkedList<Edge> queue = new LinkedList<>();
        queue.add(new Edge(0, "0"));
        mark[0] = true; // initialized true

        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                Edge removed = queue.removeFirst();
                if(removed.toVertex == destination) {
                    syso("reached destination with shortest path : Level : " + level + " :\t" + removed.path);
                } else {
                    syso("Level\t" + level + "\t" + removed.path);
                }
                for(Edge adj : graph.get(removed.toVertex)) {
                    if(mark[adj.toVertex] == false) {
                        mark[adj.toVertex] = true;
                        queue.addLast(new Edge(adj.toVertex, removed.path + " " + adj.toVertex));
                    }
                }
            }
            level++;
        }
    }

    public static void callBfsWithSizeLoop() {
        markHeading("BFS path traversed with cycle detection with within size loop");
        resetMark(mark);
        int destination = 5;
        int level = 0;

        LinkedList<Edge> queue = new LinkedList<>();
        queue.addLast(new Edge(0, "0"));
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                Edge removed = queue.removeFirst();
                if(mark[removed.toVertex] == true) {
                    syso("Cycle at Level : " + level + " :\t" + removed.path);
                    continue;
                }
                if(removed.toVertex == destination) {
                    syso("reached destination with shortest path : Level : " + level + " :\t" + removed.path);
                } else {
                    syso("Level\t" + level + "\t" + removed.path);
                }
                mark[removed.toVertex] = true;
                for(Edge adj : graph.get(removed.toVertex)) {
                    if(mark[adj.toVertex] == false) {
                        queue.add(new Edge(adj.toVertex, removed.path + " " + adj.toVertex));
                    }
                }
            }
            level++;
        }
    }

    public static void callBfsWithClassLevelVar() {
        markHeading("BFS path traversed with cycle detection with class level variable");
        resetMark(mark);
        int destination = 5;

        LinkedList<Edge> queue = new LinkedList<>();
        queue.addLast(new Edge(0, "0", 0));

        while(queue.size() > 0) {
            Edge removed = queue.removeFirst();
            if(mark[removed.toVertex] == true) {
                syso("Cycle at Level : " + removed.level + " :\t" + removed.path);
                continue;
            }
            if(removed.toVertex == destination) {
                syso("reached destination with shortest path : Level : " + removed.level + " :\t" + removed.path);
            } else {
                syso("Level\t" + removed.level + "\t" + removed.path);
            }
            mark[removed.toVertex] = true;
            for(Edge adj : graph.get(removed.toVertex)) {
                if(mark[adj.toVertex] == false) {
                    queue.addLast(new Edge(adj.toVertex, removed.path + " " + adj.toVertex, removed.level + 1));
                }
            }
        }
    }

    public static void callBfsWithDelimiter() {
        markHeading("BFS path traversed with cycle detection with null delimiter");
        resetMark(mark);
        LinkedList<Edge> queue = new LinkedList<>();
        int destination = 5;
        queue.addLast(new Edge(0, "0"));
        queue.addLast(null);
        int level = 0;
        while(queue.size() > 1) {
            Edge removed = queue.removeFirst();
            if(removed == null) {
                level++;
                queue.addLast(null);
                continue;
            }
            if(mark[removed.toVertex] == true) {
                syso("Cycle at Level : " + level + " :\t" + removed.path);
                continue;
            }
            if(removed.toVertex == destination) {
                syso("reached destination with shortest path : Level : " + level + " :\t" + removed.path);
            } else {
                syso("Level\t" + level + "\t" + removed.path);
            }
            mark[removed.toVertex] = true;
            for(Edge adj : graph.get(removed.toVertex)) {
                if(mark[adj.toVertex] == false) {
                    queue.addLast(new Edge(adj.toVertex, removed.path + " " + adj.toVertex));
                }
            }
        }
    }

    public static void callBfs() {
        markHeading("BFS path traversed with cycle detection basic");
        LinkedList<Edge> queue = new LinkedList<>();

        int destination = 5;
        queue.add(new Edge(0, "0"));

        while(queue.size() > 0) {
            Edge removed = queue.removeFirst();
            if(mark[removed.toVertex] == true) {
                syso("cycle at : " + removed.path);
                continue;
            }
            if(removed.toVertex == destination) {
                syso("reached destination with shortest path : " + removed.path);
            } else {
                syso(removed.path);
            }
            mark[removed.toVertex] = true;
            for(Edge adjacent : graph.get(removed.toVertex)) {
                if(mark[adjacent.toVertex] == false) {
                    queue.addLast(new Edge(adjacent.toVertex, removed.path + " " + adjacent.toVertex));
                }
            }
        }
    }

    // code Ends for BFS

    // Only utility method below

    static class Edge {
        int toVertex;
        String path;
        int level;

        public Edge(int toVertex, String path, int level) {
            this.toVertex = toVertex;
            this.path = path;
            this.level = level;
        }

        public Edge(int toVertex, String path) {
            this.toVertex = toVertex;
            this.path = path;
        }

        public Edge(int toVertex) {
            this.toVertex = toVertex;
        }

        @Override
        public String toString() {
            return (path == null ? String.valueOf(toVertex) : path);
        }
    }

    public static int totalNodes;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static boolean mark[];
    
    public static void constructGraph() {
        Scanner scanner = new Scanner(System.in);
        totalNodes = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < totalNodes; i++) graph.add(new ArrayList<Edge>());
        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        scanner.close();
        mark = new boolean[totalNodes];
    }

    public static void addEdge(int v, int u) {
        graph.get(v).add(new Edge(u));
        graph.get(u).add(new Edge(v));
    }

    public static void displayGraph() {
        for(int i = 0; i < totalNodes; i++) {
            System.out.println(i + " -> " + graph.get(i) + ", ");
        }
    }

    public static void resetMark(boolean[] array) {
        for(int i = 0; i < array.length; i++) array[i] = false;
    }

    // random utility methods
    public static final int FIXED_LEN = 135;
    public static void printLine() {
        System.out.println(String.format("%140s", " ").replaceAll(" ", "*"));
    }

    public static void printLine(String word) {
        printNewLine();
        int write = word.length();
        int fill = (FIXED_LEN - write) / 2;
        System.out.println(String.format("%" + fill + "s", " ").replaceAll(" ", "*") + " : " + word + " : "
                + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "*"));
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void syso(Object obj) {
        System.out.println(obj);
    }

    public static void markHeading(String string) {
        printNewLine();
        printLine();
        syso(string);
        printLine();
        printNewLine();
    }
}