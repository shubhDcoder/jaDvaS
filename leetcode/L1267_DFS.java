public class L1267_DFS {
    public static void main(String args[]) {
        // int[][] grid = {{1,0},{0,1}};
        // int[][] grid = {{1,0},{1,1}};
        int[][] grid = {{1, 1, 0, 0},{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        System.out.println(new L1267_DFS().countServers(grid));
    }

    public int countServers(int[][] grid) {
        if(grid.length == 0) return 0;

        int totalServers = 0;
        int totalAlones = 0;
        int[] rowColumn = new int[grid.length];
        int[] colColumn = new int[grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    totalServers++;
                    rowColumn[i] += 1;
                    colColumn[j] += 1;
                }
            }
        }

        for(int i = 0; i < grid.length; i++) {
            if(rowColumn[i] == 1) {
                for(int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == 1 && colColumn[j] == 1)
                        totalAlones++;
                }
            }
        }

        return totalServers - totalAlones;
    }
}