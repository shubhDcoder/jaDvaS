import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EOEO {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter pw = new PrintWriter(System.out);
  static int MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    EOEO m = new EOEO();
    m.solve();
    m.close();
  }

  void solve() throws IOException {
    int totalTestCase = readInt();
    while (totalTestCase-- > 0) {
      long ts = readLong();
      if ((ts & (ts - 1)) == 0) {
        pw.println(0);
        continue;
      }
      while ((ts & 1) != 1) {
        ts = (ts >> 1);
      }
      pw.println((ts - 1) / 2);
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
