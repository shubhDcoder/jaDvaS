public class LexicographicallySortedString {
    public static void main(String args[]) {
        int start = 1;
        int end = 100;

        LexicalOrderInternal(1, 10, 100);
    }

    private static void LexicalOrderInternal(int init, int end, int n) {
        for (int i = init; i < end; i++) {
            if (i > n) return;
            System.out.println(i);
            if (10 * i <= n) LexicalOrderInternal(10 * i, 10 * i + 10, n); //Tree pruning here!
        }
    }
}