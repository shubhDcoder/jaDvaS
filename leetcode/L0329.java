import java.util.*;

public class L0329 {

    public static void main(String args[]) {
        int[][] nums = {
                        {9,9,4},
                        {6,6,8},
                        {2,1,1}};
        System.out.println(new L0329().longestIncreasingPath(nums));
    }

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;

        int[][] dir = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        LinkedList<Integer> queue = new LinkedList<>();

        int[][] inorder = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                for(int k = 0; k < dir.length; k++) {
                    int x = i + dir[k][0];
                    int y = j + dir[k][1];
                    if(x >= 0 && y >=0 && x < matrix.length && y < matrix[i].length) {
                        if(matrix[i][j] < matrix[x][y]) {
                            inorder[x][y]++;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < inorder.length; i++) {
            for(int j = 0; j < inorder[i].length; j++) {
                if(inorder[i][j] == 0) {
                    queue.addLast(i * inorder.length + j);
                }
            }
        }

        int level = 0;
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.removeFirst();
                int i = removed / matrix[0].length;
                int j = removed % matrix[0].length;

                for(int k = 0; k < dir.length; k++) {
                    int x = (removed / matrix[0].length) + dir[k][0];
                    int y = (removed % matrix[0].length) + dir[k][1];
                    if(x >= 0 && y >=0 && x < matrix.length && y < matrix[i].length) {
                        if(matrix[i][j] < matrix[x][y]) {
                            inorder[x][y]--;
                            if(inorder[x][y] == 0) queue.push(x * matrix[0].length + y);
                        }
                    }
                }
            }
            level++;
        }

        // System.out.println(level);

        // Arrays.stream(matrix).forEach(e -> System.out.println(Arrays.toString(e)));
        // System.out.println();
        // Arrays.stream(inorder).forEach(e -> System.out.println(Arrays.toString(e)));

        return level;
    }
}