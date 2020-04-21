public class L0463_DFS {
    public static void main(String args[]) {
        int[][] grid =  {{0,1,0,0},
                        {1,1,1,0},
                        {0,1,0,0},
                        {1,1,0,0}};
        System.out.println("perimeter is : " + new L0463_DFS().islandPerimeter(grid));
    }

    public int islandPerimeter(int[][] grid) {
        if(grid.length == 0) return 0;

        int totalBox = 0;
        int commonPerimeter = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    totalBox++;
                    if(j + 1 < grid[0].length && grid[i][j+1] == 1) commonPerimeter++;
                    if(i + 1 < grid.length && grid[i + 1][j] == 1) commonPerimeter++;
                }
            }
        }

        return (totalBox * 4) - (2 * commonPerimeter);
    }
}