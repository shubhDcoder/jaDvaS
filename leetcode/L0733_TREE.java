import java.util.Arrays;

public class L0733_TREE {
  int direction[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

  public static void main(String[] args) {
    // int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
    int image[][] = {{0, 0, 0}, {0, 1, 1}};
    new L0733_TREE().floodFill(image, 1, 1, 1);
    for (int[] arr : image) System.out.println(Arrays.toString(arr));
  }

  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    callDFS(image, sr, sc, newColor, image[sr][sc]);
    return image;
  }

  public void callDFS(int[][] image, int sr, int sc, int newColor, int oldColor) {
    image[sr][sc] = newColor;
    for (int[] dir : direction) {
      int nr = dir[0] + sr;
      int nc = dir[1] + sc;
      if (nr < image.length
          && nc < image[0].length
          && nr >= 0
          && nc >= 0
          && image[nr][nc] == oldColor) callDFS(image, nr, nc, newColor, oldColor);
    }
  }
}
