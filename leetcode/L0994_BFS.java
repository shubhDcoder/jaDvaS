import java.util.*;

public class L0994_BFS {
    public static void main(String args[]) {
        // int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        // int[][] grid = new int[][]{{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid = new int[][]{{0,2}};
        System.out.println(new L0994_BFS().orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 2) queue.offer(i * grid[0].length + j);
            }
        }


        byte direction[][] = new byte[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int level = 0;
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.poll();
                for(byte[] dir : direction) {
                    int nx = removed / grid[0].length + dir[0];
                    int ny = removed % grid[0].length + dir[1];
                    if(nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] == 1) {
                        queue.offer(nx * grid[0].length + ny);
                        grid[nx][ny] = 2;
                    }
                }
            }
            level++;
        }

        for(int[] arr : grid)
            for(int ele : arr) 
                if(ele == 1) return -1;
        
        return level - 1;
    }
}