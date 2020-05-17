import java.util.Queue;
import java.util.LinkedList;

public class L1091_BFS {
    public static void main(String args[]) {
        // int[][] grid = new int[][]{{0,1},{1,0}};

        // int[][] grid = new int[][]{{0,0,0},{1,1,0},{1,1,0}};

        int[][] grid = new int[][]{{0,0,1,0,0,0,0},{0,1,0,0,0,0,1},{0,0,1,0,1,0,0},{0,0,0,1,1,1,0},{1,0,0,1,1,0,0},{1,1,1,1,1,0,1},{0,0,1,0,0,0,0}};
        System.out.println(new L1091_BFS().shortestPathBinaryMatrix(grid));
    }

    // static class Edge {
    //     int x;
    //     int y;
    //     int level;

    //     public Edge(int x, int y, int level) {
    //         this.x = x;
    //         this.y = y;
    //         this.level = level;
    //     }
    // }

    byte[][] directions = new byte[][]{{1, 0}, {0, 1}, {1, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}};
    boolean[] visited;
    public int shortestPathBinaryMatrix(int[][] grid) { 
        if(grid.length == 0 || grid[0].length == 0 || grid[0][0] == 1 || grid[grid.length - 1][grid.length - 1] == 1) return -1;
        if(grid.length == 1 && grid[0][0] == 0) return 1;
        int N = grid.length;
        visited = new boolean[N * N];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int level = 1;
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.poll();

                for(byte[] dir : directions) {
                    int nx = (removed / N) + dir[0];
                    int ny = (removed % N) + dir[1];

                    int ind = nx * N + ny;                

                    if(nx >= 0 && ny >= 0 && nx < grid.length && ny < grid.length && grid[nx][ny] == 0 && visited[ind] == false) {
                        if(nx == grid.length - 1 && ny == grid.length - 1) return level + 1;
                        queue.offer(ind);
                        visited[ind] = true;
                    } 
                }
            }
            level++;
        }

        return -1;
    }
}