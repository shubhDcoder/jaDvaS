import static java.lang.System.out;

public class MazePathFinder {
    public static final int FIXED_LEN = 106;

    static class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public String toString() {
            return "[" + x +" : " + y + "]";
        }
    }



    public static void main(String args[]) {
        printLine("MAZE PATH FINDER");
        printLine();

        printLine("With only H & V allowed (source will always be above/inline wrt dest.)");
        out.print("\nTotal paths are : " + finderHV_forward_down(new Point(0, 0), new Point(2, 2), ""));
        printLine("With only H & V & D allowed (source will always be above/inline wrt dest.)");
        out.print("\nTotal paths are : " + finderHVD_forward_down_diagonal(new Point(0, 0), new Point(2, 2), ""));
    }

    public static int finderHVD_forward_down_diagonal(Point source, Point dest, String answer) {
        if(source.equals(dest)) {
            out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        if(source.x <= dest.x && source.y <= dest.y) {
            Point newHorizontal = new Point(source.x + 1, source.y);
            count = count + finderHVD_forward_down_diagonal(newHorizontal, dest, answer + "H");

            Point newDiagonal = new Point(source.x + 1, source.y + 1);
            count = count + finderHVD_forward_down_diagonal(newDiagonal, dest, answer + "D");

            Point newVertical = new Point(source.x, source.y + 1);
            count = count + finderHVD_forward_down_diagonal(newVertical, dest, answer + "V");
        }
        return count;
    }

    public static int finderHV_forward_down(Point source, Point dest, String answer) {
        if(source.equals(dest)) {
            out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        if(source.x <= dest.x && source.y <= dest.y) {
            Point newHorizontal = new Point(source.x + 1, source.y);
            count = count + finderHV_forward_down(newHorizontal, dest, answer + "H");

            Point newVertical = new Point(source.x, source.y + 1);
            count = count + finderHV_forward_down(newVertical, dest, answer + "V");
        }
        return count;
    }

    // random utility methods
    public static void printLine() {
        out.println(String.format("%112s", " ").replaceAll(" ", "="));
    }

    public static void printLine(String word) {
        printNewLine();
        int write = word.length();
        int fill = (FIXED_LEN - write) / 2;
        out.println(String.format("%" + fill + "s", " ").replaceAll(" ", "=") + " : " + word + " : " + 
                    String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "="));
    }

    public static void printNewLine() {
        out.println();
    }

}