import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class XYSTR {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter pw = new PrintWriter(System.out);
  static int MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    XYSTR m = new XYSTR();
    m.solve();
    m.close();
  }

  void solve() throws IOException {
    int totalTestCase = readInt();
    while (totalTestCase-- > 0) {
      char[] input = br.readLine().toCharArray();
      int ans = 0;
      for (int i = 0; i < input.length - 1; ) {
        if ((input[i] == 'x' && input[i + 1] == 'y') || (input[i] == 'y' && input[i + 1] == 'x')) {
          ans += 1;
          i += 2;
          continue;
        }
        i += 1;
      }
      pw.println(ans);
    }
  }

  void close() throws IOException {
    pw.flush();
    pw.close();
    br.close();
  }

  int readInt() throws IOException {
    return Integer.parseInt(br.readLine());
  }

  long readLong() throws IOException {
    return Long.parseLong(br.readLine());
  }

  int[] readIntLine() throws IOException {
    String[] tokens = br.readLine().split(" ");
    int[] A = new int[tokens.length];
    for (int i = 0; i < A.length; i++) A[i] = Integer.parseInt(tokens[i]);
    return A;
  }

  long[] readLongLine() throws IOException {
    String[] tokens = br.readLine().split(" ");
    long[] A = new long[tokens.length];
    for (int i = 0; i < A.length; i++) A[i] = Long.parseLong(tokens[i]);
    return A;
  }
}
