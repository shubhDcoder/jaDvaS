public class L1020_DFS {
  byte[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public int numEnclaves(int[][] A) {
    // first row
    for (int i = 0; i < A[0].length; i++) if (A[0][i] == 1) callDFS(A, 0, i);
    // first column
    for (int i = 0; i < A.length; i++) if (A[i][0] == 1) callDFS(A, i, 0);
    // last row
    for (int i = 0; i < A[0].length; i++) if (A[A.length - 1][i] == 1) callDFS(A, A.length - 1, i);
    // last column
    for (int i = 0; i < A.length; i++)
      if (A[i][A[0].length - 1] == 1) callDFS(A, i, A[0].length - 1);
    int ans = 0;
    for (int i = 1; i < A.length; i++)
      for (int j = 1; j < A[0].length; j++) if (A[i][j] == 1) ans++;

    return ans;
  }

  public void callDFS(int[][] m, int sr, int sc) {
    m[sr][sc] = 2;
    for (byte[] dir : direction) {
      int nr = sr + dir[0];
      int nc = sc + dir[1];
      if (nr < m.length && nr >= 0 && nc < m[0].length && nc >= 0 && m[nr][nc] == 1) {
        callDFS(m, nr, nc);
      }
    }
  }
}
