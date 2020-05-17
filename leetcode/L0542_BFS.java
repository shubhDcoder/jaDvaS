import java.util.*;

public class L0542_BFS {
    public static void main(String args[]) {

        // int[][] matrix = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};

        int[][] answer = new L0542_BFS().updateMatrix(matrix);
        for(int[] ans : answer) 
            System.out.println(Arrays.toString(ans));
    }

    public int[][] updateMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        boolean visited[][] = new boolean[N][M];

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(matrix[i][j] == 0) {
                    queue.offer(i * M + j);
                    visited[i][j] = true;
                }
            }
        }

        byte[][] direction = new byte[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
        int level = 1;
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.poll();
                for(byte[] dir : direction) {
                    int x = removed / M;
                    int y = removed % M;
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] == false) {
                        visited[nx][ny] = true;
                        if(matrix[x][y] != 0) {
                            matrix[nx][ny] = matrix[x][y] + 1;
                            continue;
                        }
                        matrix[nx][ny] = level;
                        queue.offer(nx * M + ny);
                    }
                }
            }
            level++;
        }

        return matrix;
    }
}