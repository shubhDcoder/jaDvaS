import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TTUPLE {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter pw = new PrintWriter(System.out);
  static int MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    TTUPLE m = new TTUPLE();
    m.solve();
    m.close();
  }

  void solve() throws IOException {
    int totalTestCase = readInt();
    while (totalTestCase-- > 0) {
      int[] source = readIntLine();
      int[] dest = readIntLine();
      int a = dest[0] - source[1];
      int b = dest[1] - source[1];
      int c = dest[2] - source[2];
      if ((a == b) && (b == c)) {
        pw.println(1);
        continue;
      }

      if (dest[0] % source[0] == 0) {
        int x = dest[0] / source[0];
        if (dest[1] % source[1] == 0 && dest[1] / source[1] == x) {
          if (dest[2] % source[2] == 0 && dest[2] / source[2] == x) {
            pw.println(1);
            continue;
          }
        }
      }
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
