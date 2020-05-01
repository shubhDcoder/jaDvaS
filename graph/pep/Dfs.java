import java.util.*;

public class Dfs extends GraphUtil {
    public static void main(String args[]) {
        markHeading("DFS");
        fillGraph();
        displayGraph();
        printLine("DFS path traversed are ");
        for(int i = 0; i < graph.size(); i++) {
            if(mark[i] == false)
                callDfsUsingRecursion(i, i + "");
        }

        printNewLine();
    }

    public static void callDfsUsingRecursion(int index, String path) {
        mark[index] = true;
        boolean result = false;
        for(Edge edge : graph.get(index)) {
            if(mark[edge.toVertex] == false) {
                result = true;
                callDfsUsingRecursion(edge.toVertex, "\t" + path + " > " + edge.toVertex);
            }
        }
        if(!result) syso("path --> " + path);
    }
}