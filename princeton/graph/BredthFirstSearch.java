import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class BredthFirstSearch {
    public static void main(String args[]) throws FileNotFoundException {
        if(args.length == 0) throw new IllegalArgumentException("Please provide an input file");
        
        // constructing graph
        Scanner scanner = new Scanner(new File(args[0]));
        Graph graph = new Graph(scanner);
        scanner.close();

        // preparing needed variables
        BredthFirstSearch search = new BredthFirstSearch();
        int src = (args.length > 1) ? Integer.parseInt(args[1]) : 0;

        PrintUtil.markHeading("Bredth First Search");
        PrintUtil.printLine("graph looks like");
        PrintUtil.syso(graph);
        
        // call for DFS
        PrintUtil.printLine();
        PrintUtil.syso("BFS traversing -- ");
        search.callBfs(graph, src);
        // PrintUtil.syso("total recursive calls made is equals to number of vertex in source connected graph : " + search.count);

        // to check paths from source to all destinations in same time
        PrintUtil.printLine();
        PrintUtil.syso("Path from 0 to every node are");
        search.showAllPaths(graph, 0);
    }

    public void showAllPaths(Graph graph, int source) {
        BredthFirstSearchPaths paths = new BredthFirstSearchPaths(graph, source);
        for(int i = 0; i < graph.tVertex; i++) {
            if(paths.hasPath(i)) {
                System.out.print(source + " to " + i + " : ");
                for(int p : paths.getPath(i)) {
                    if(p == source) System.out.print(p);
                    else System.out.print("-" + p);
                }
                PrintUtil.printNewLine();
            }
        }
    }

    public void callBfs(Graph graph, int source) {
        boolean[] visited = new boolean[graph.tVertex];
        String[] paths = new String[graph.tVertex];
        for(int i = 0; i < graph.tVertex; i++) paths[i] = "";
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source);
        paths[source] = source + "";
        visited[source] = true;

        while(queue.size() > 0) {
            int removed = queue.poll();
            System.out.println(paths[removed]);
            for(int adj : graph.adj(removed)) {
                if(!visited[adj]) {
                    visited[adj] = true;
                    queue.offer(adj);
                    paths[adj] = paths[removed] + "-" + adj;
                }
            }
        }
    }
}