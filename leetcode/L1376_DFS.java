import java.util.*;

public class L1376_DFS {
    public static void main(String args[]) {
    int n = 1, headID = 0; 
    int[] manager = {-1};
    int[] informTime = {0};

    // int n = 6, headID = 2; 
    // int[] manager = {2,2,-1,2,2,2};
    // int[] informTime = {0,0,1,0,0,0};

    // int n = 7, headID = 6; 
    // int[] manager = {1,2,3,4,5,6,-1};
    // int[] informTime = {0,6,5,4,3,2,1};

    // int n = 15, headID = 0; 
    // int[] manager = {-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6};
    // int[] informTime = {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};

    // int n = 4, headID = 2; 
    // int[] manager = {3,3,-1,2};
    // int[] informTime = {0,0,162,914};

    System.out.println(new L1376_DFS().numOfMinutes(n, headID, manager, informTime));
    }

    /*
        Just recursion 8 ms
    */

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int result = 0;
        for(int i = 0; i < n; i++) {
            result = Math.max(result, callDfs(i, manager, informTime));
        }
        return result;
    }

    public int callDfs(int s, int[] manager, int[] informTime) {
        if(manager[s] == -1) return informTime[s];
        informTime[s] += callDfs(manager[s], manager, informTime);
        manager[s] = -1;
        return informTime[s];
    }


    /*
        DFS approach 80ms
    */

    // List<List<Integer>> graph;
    // public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
    //     graph = new ArrayList<>(n);
    //     for(int i = 0; i < n; i++) graph.add(new ArrayList<>());

    //     for(int i = 0; i < n; i++) {
    //         if(i == headID) continue;
    //         addEdge(manager[i], i);
    //     }

    //     return callDfs(headID, informTime);
    // }

    // public void addEdge(int p, int c) {
    //     graph.get(p).add(c);
    // }

    // public int callDfs(int source, int[] informTime) {
    //     int childTime = 0;
    //     for(int adj : graph.get(source)) {
    //         childTime = Math.max(childTime, callDfs(adj, informTime));
    //     }
    //     return informTime[source] + childTime;
    // }
}