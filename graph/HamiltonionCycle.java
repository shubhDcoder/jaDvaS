import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HamiltonionCycle {
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
        fillGraph();
        markHeading("Detect if there is hamilton cycle");
        syso("\ntotal hamilton paths are : " + isHamiltonion(0, 0, 0, " 0 > ", 0));
    }

    public static int isHamiltonion(int current, int target, int weight, String answer, int marked) {
        if(marked == graph.size() - 1) {
            String print = "found a hamilton path : " + answer + " with weight " + weight;
            if(findIndexOfVertex(current, target) != -1)
                print += " < its a cycle";
            syso(print);
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

        public static int findIndexOfVertex(int u, int v) {
        List<Edge> list = graph.get(u);
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).toVertex == v) return i;
        }
        return -1;
    }

    public static boolean[] mark = new boolean[10];
    public static void fillGraph() {

        // assign arraylist to default null of graph
        for(int i = 0; i < 10; i++) graph.add(new ArrayList<Edge>());

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