import java.util.*;

public class GraphBasic {
    static class Edge {
        int toVertex;
        int weight;

        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("[%2d @ %2d ]", toVertex, weight);
        }
    }

    public static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String args[]) {
        markHeading("Graph theory");
        fillGraph();
        displayGraph();

        printNewLine();

        printLine(" removing 0 - 3 edge");
        removeEdge(0, 3);
        displayGraph();
        addEdge(0, 3, 2);

        printLine(" removing entire vertex ");
        // reomveVertex(6);
        syso("uncomment code line to remove vertex");
        // displayGraph();

        printLine(" check to see if path exists between two nodes");
        syso("\nresult " + hasPath(0, 9, 0, "0 > "));

        resetMark(mark);
        printLine("Total number of paths between two vertex ");
        syso("\nresult : total number of paths between 0 to 9 are " + totalPath(0, 9, 0, "0 > "));

        resetMark(mark);
        printLine("get max / min / ceil / floor of all paths between two vertices");
        getStats(0, 9, 0);
        syso("result : \n\tmax " + max + "\n\tmin " + min + "\n\tceil " + ceil + "\n\tfloor " + floor);

        resetMark(mark);
        printLine("check to see if its a hamiltonion cycle");
        syso("\nresult : total paths are " + isHamiltonion(0, 0, 0, "0 > ", 0));

        resetMark(mark);
        printLine("check to see number of non connected components");
        syso("\nresult : total un connected subgraphs are " + findComponents());
    }

    public static int findComponents() {
        int count = 0;
        int maxElements = Integer.MIN_VALUE;
        for(int i = 0; i < graph.size(); i++) {
            if(graph.get(i).size() != 0) {
                if(mark[i] == false) {
                    // syso("doing for " + i);
                    count++;
                    maxElements = Math.max(callDfs(i) + 1, maxElements);
                }
            }
        }
        syso("\nmaximum collected elements are " + maxElements);
        return count;
    }

    public static int callDfs(int source) {
        int count = 0;
        mark[source] = true;;
        for(Edge edge : graph.get(source))
            if(mark[edge.toVertex] == false)
                count = count + callDfs(edge.toVertex) + 1;

        return count;
    }

    public static int isHamiltonion(int current, int target, int weight, String answer, int marked) {
        if(marked == graph.size() - 1) {
            syso("found a hamilton path : " + answer + " with weight " + weight);
            if(findIndexOfVertex(current, target) != -1)
                syso("its a cycle");
            return 1;
        }

        int count = 0;
        mark[current] = true;
        List<Edge> list = graph.get(current);
        for(Edge edge : list) {
            if(mark[edge.toVertex] == false) 
                count += isHamiltonion(edge.toVertex, target, weight + edge.weight, answer + edge.toVertex + " > ", marked + 1);
        }
        mark[current] = false;
        return count;
    }


    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;
    public static int ceil = Integer.MAX_VALUE;
    public static int floor = Integer.MIN_VALUE;
    public static int calculateFor = 34;
    public static void getStats(int u, int v, int weight) {
        if(u == v) {
            max = Math.max(weight, max);
            min = Math.min(weight, min);
            if(weight > calculateFor) ceil = Math.min(ceil, weight);
            if(weight < calculateFor) floor = Math.max(weight, floor);
            return;
        }
        mark[u] = true;
        for(Edge edge : graph.get(u)) {
            if(mark[edge.toVertex] == false) 
                getStats(edge.toVertex, v, weight + edge.weight);
        }
        mark[u] = false;
    }

    public static boolean[] mark = new boolean[17];
    public static int totalPath(int u, int v, int weight, String answer) {
        if(u == v) {
            syso("got a path " + answer + " with weight " + weight);
            return 1;
        }

        int count = 0;
        mark[u] = true;
        for(Edge edge : graph.get(u)) {
            if(mark[edge.toVertex] == false) {
                count += totalPath(edge.toVertex, v, weight + edge.weight, answer + edge.toVertex + " > ");
            }
        }
        mark[u] = false;

        return count;
    }

    public static boolean hasPath(int u, int v, int weight, String answer) {
        if(u == v) {
            System.out.println("got a path " + answer + " with weight " + weight);
            return true;
        }

        boolean result = false;
        mark[u] = true;
        for(Edge edge : graph.get(u)) {
            if(mark[edge.toVertex] == false) {
                result = result || hasPath(edge.toVertex, v, weight + edge.weight, answer + edge.toVertex + " > ");
            }
        }
        return result;
    }

    public static void fillGraph() {

        // assign arraylist to default null of graph
        for(int i = 0; i < 17; i++) graph.add(new ArrayList<Edge>());

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
        }
        scanner.close();
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

    public static void removeEdge(int u, int v) {
        int index = findIndexOfVertex(u, v);
        if(index != -1) graph.get(u).remove(index);
        index = findIndexOfVertex(v, u);
        if(index != -1) graph.get(v).remove(index);
    }

    public static void reomveVertex(int u) {
        List<Edge> list = graph.get(u);
        for(int i = list.size() - 1; i >= 0 ; i--) {
            Edge remove = list.get(i);
            removeEdge(u, remove.toVertex);
        }
    }

    public static int findIndexOfVertex(int u, int v) {
        List<Edge> list = graph.get(u);
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).toVertex == v) return i;
        }
        return -1;
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