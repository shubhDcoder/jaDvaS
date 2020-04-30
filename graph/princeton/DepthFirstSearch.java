import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class DepthFirstSearch {

    public boolean mark[];
    public int count;

    public static void main(String args[]) throws FileNotFoundException{
        if(args.length == 0) throw new IllegalArgumentException("Please provide an input file");
        
        // constructing graph
        Scanner scanner = new Scanner(new File(args[0]));
        Graph graph = new Graph(scanner);
        scanner.close();

        // preparing needed variables
        DepthFirstSearch search = new DepthFirstSearch();
        search.mark = new boolean[graph.tVertex];
        int src = (args.length > 1) ? Integer.parseInt(args[1]) : 0;

        PrintUtil.markHeading("Depth First Search");
        PrintUtil.printLine("graph looks like");
        PrintUtil.syso(graph);
        
        // call for DFS
        PrintUtil.printLine();
        PrintUtil.syso("DFS traversing -- ");
        search.callDfs(graph, src, src + "");
        PrintUtil.syso("total recursive calls made is equals to number of vertex in source connected graph : " + search.count);

        // to check paths from source to all destinations in same time
        PrintUtil.printLine();
        PrintUtil.syso("Path from 0 to every node are");
        search.showAllPaths(graph, 0);
    }

    public void showAllPaths(Graph graph, int source) {
        DepthFirstSearchPaths paths = new DepthFirstSearchPaths(graph, source);
        for(int i = 0; i < graph.tVertex; i++) {            
            if(paths.hasPath(i)) {
                System.out.print(source + " to " + i + " : ");
                for(int x : paths.getPath(i)) {
                    if(x == source) System.out.print(x);
                    else System.out.print("-" + x);
                }
            }
            PrintUtil.printNewLine();
        }
    }

    public void callDfs(Graph graph, int source, String toPrint) {
        count++;
        PrintUtil.syso(toPrint);
        mark[source] = true;
        for(int adj : graph.adj(source)) {
            if(mark[adj] == false) {
                callDfs(graph, adj, toPrint + "\t" + adj);
            }
        }
    }
}