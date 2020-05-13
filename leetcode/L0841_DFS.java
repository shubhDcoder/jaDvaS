import java.util.*;

public class L0841_DFS {
    public static void main(String args[]) {
        // int[][] ints = {{1},{2},{3},{}};
        int[][] ints = {{1,3},{3,0,1},{2},{0}};

        List<List<Integer>> list = new ArrayList<>();
        for(int[] arr : ints) {
            List<Integer> temp = new ArrayList<>();
            for(int a : arr) temp.add(a);
            list.add(temp);
        }
        // System.out.println(new L0841_DFS().canVisitAllRooms(list));
    }

    byte[] visited;
    int traversed;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        visited = new byte[N];

        // for(int from = 0; from < N; from++) {
            // if(visited[from] == 0) {
                callDfs(0, rooms);
            // }
        // }
        System.out.println(traversed);
        return traversed == N;
    }

    public void callDfs(int source, List<List<Integer>> rooms) {
        visited[source] = 1;
        traversed++;
        for(int to : rooms.get(source)) {
            if(visited[to] == 0) {
                callDfs(to, rooms);
            }
        }
    }
}