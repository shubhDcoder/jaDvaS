import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PRICECON {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter pw = new PrintWriter(System.out);
  static int MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    PRICECON m = new PRICECON();
    m.solve();
    m.close();
  }

  void solve() throws IOException {
    int totalTestCase = readInt();
    while (totalTestCase-- > 0) {
      String arg[] = br.readLine().split(" ");
      int ceil = Integer.parseInt(arg[1]);
      Integer.parseInt(arg[0]);
      int sum = 0;
      int totalSum = 0;
      for (String n : br.readLine().split(" ")) {
        int nmbr = Integer.parseInt(n);
        if (nmbr > ceil) sum += ceil;
        else sum += nmbr;
        totalSum += nmbr;
      }
      System.out.println(totalSum - sum);
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
