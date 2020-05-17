import java.util.*;

public class L0785_BFS {
    public static void main(String args[]) {
        // int[][] graph = new int[][]{{1,3}, {0,2}, {1,3}, {0,2}};
        int[][] graph = new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};
        // int[][] graph = new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        System.out.println(new L0785_BFS().isBipartite(graph));
    }

    boolean[] visited;
    boolean[] markedWith;
    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        markedWith = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++) {
            if(visited[i] == false) {
                if(isBipartite(i, graph) == false) return false; 
            }
        }
        return true;
    }

    // using BFS
    // public boolean isBipartite(int source, int[][] graph) {
    //     Queue<Integer> queue = new LinkedList<>();
    //     queue.offer(source);
    //     visited[source] = true;

    //     while(queue.size() > 0) {
    //         int removed = queue.poll();
    //         for(int adj : graph[removed]) {
    //             if(visited[adj] == false) {
    //                 queue.offer(adj);
    //                 markedWith[adj] = !markedWith[removed];
    //                 visited[adj] = true;
    //             } else if(markedWith[adj] == markedWith[removed]) {
    //                 return false;
    //             }
    //         }
    //     }

    //     return true;
    // }

    // using DFS
    public boolean isBipartite(int source, int[][] graph) {
        visited[source] = true;
        for(int adj : graph[source]) {
            if(visited[adj] == false) {
                markedWith[adj] = !markedWith[source];
                if(isBipartite(adj, graph) == false) return false;
            } else if(markedWith[adj] == markedWith[source]) {
                return false;
            }
        }
        return true;
    }
}