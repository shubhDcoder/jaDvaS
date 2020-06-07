import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CHFICRM {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter pw = new PrintWriter(System.out);
  static int MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    CHFICRM m = new CHFICRM();
    m.solve();
    m.close();
  }

  void solve() throws IOException {
    int totalTestCase = readInt();
    while (totalTestCase-- > 0) {
      readInt();
      int five = 0, ten = 0, fifteen = 0;
      int[] coins = readIntLine();
      boolean result = true;
      for (int coin : coins) {
        if (coin == 5) five++;
        else if (coin == 10 && five > 0) {
          ten++;
          five--;
        } else if (coin == 15 && (ten > 0 || five > 1)) {
          fifteen++;
          if (ten > 0) ten--;
          else if (five > 1) five -= 2;
        } else {
          result = false;
          break;
        }
      }
      if (result) pw.println("YES");
      else pw.println("NO");
      pw.println("five " + five + " : " + ten + " : " + fifteen);
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
