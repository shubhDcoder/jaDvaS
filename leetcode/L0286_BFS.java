import java.util.*;

public class L0286_BFS {
    public static void main(String args[]) {
        int[][] grid = new int[][]{{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        L0286_BFS obj = new L0286_BFS();
        obj.wallsAndGates(grid);
        for(int[] arr : grid) System.out.println(Arrays.toString(arr));
    }

    boolean visited[][];
    public void wallsAndGates(int[][] rooms) {
        visited = new boolean[rooms.length][rooms[0].length];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(i * rooms[0].length + j);
                    visited[i][j] = true;
                }
            }
        }

        callBFS(queue, rooms);
    }

    byte[][] directions = new byte[][] {{0,1}, {1,0}, {-1,0}, {0, -1}};
    public void callBFS(Queue<Integer> queue, int[][] rooms) {
        int level = 0;
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.poll();
                for(byte[] dir : directions) {
                    int nx = removed / rooms[0].length + dir[0];
                    int ny = removed % rooms[0].length + dir[1];
                    if(nx >= 0 && ny >= 0 && nx < rooms.length && ny < rooms[0].length && visited[nx][ny] == false && rooms[nx][ny] == Integer.MAX_VALUE) {
                        queue.offer(nx * rooms[0].length + ny);
                        rooms[nx][ny] = level + 1;
                    }
                }
            }
            level++;
        }
    }
}