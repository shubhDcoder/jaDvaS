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
            return "[" + x + " : " + y + "]";
        }
    }

    static class Path {
        int len;
        String name;

        public Path(int length, String name) {
            this.len = length;
            this.name = name;
        }

        public Path(String name) {
            this.name = name;
        }

        public Path() {}

        @Override
        public String toString() {
            return "[length : " + len + ", name : " + name + "]";
        }
    }
    
    public static void main(String args[]) {
        printLine("MAZE PATH FINDER");
        printLine();

        printLine("With only H & V allowed (source will always be above/inline wrt dest.)");
        out.print("\nTotal paths are : " + finderHV_forward_down(new Point(0, 0), new Point(2, 2), ""));
        printLine("With only H & V & D allowed (source will always be above/inline wrt dest.)");
        out.print("\nTotal paths are : " + finderHVD_forward_down_diagonal(new Point(0, 0), new Point(2, 2), ""));
        printLine("With only H & V & D allowed (source will always be above/inline wrt dest.) - MULTIMOVE ALLOWED");
        out.print("\nTotal paths are : " + finderHVD_forward_down_diagonal_multimove(new Point(0, 0), new Point(2, 2), ""));
        printLine("With only H & V allowed only forward/backward/up/down");
        out.print("\nTotal paths are : " + finderHV_UD(new Point(0, 0), new Point(2, 2), ""));
        printLine("With only H & V allowed. only forward/backward/up/down -- MULTIMOVE ALLOWED");
        out.print("\nTotal paths are : " + finderHV_UD_multimove(new Point(0, 0), new Point(2, 2), "", 2));

        printLine("With only H & V & D allowed only forward/backward/up/down/diagonal");
        out.print("\nTotal paths are : " + finderHV_UD_8d(new Point(0, 0), new Point(2, 2), "", 1));

        printLine("Get the height of shortest path in HVD along with the trace");
        out.print("\nShortest paths size : " + getHeightOfShortestPath_HVD(new Point(0, 0), new Point(2, 2), new Path("")));
        
        printLine("Get the height of shortest path in HVD 8 direction with MULTIMOVE along with the trace");
        out.print("\nShortest paths size : " + getHeightOfShortestPath_8D(new Point(0, 0), new Point(2, 2), 2));
    }
    
    // IN HVD multiMove backward and down allowed
    // public static Point directions_8d[] = { new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1),
    //         new Point(1, 1), new Point(-1, 1), new Point(-1, -1), new Point(1, -1) };
    // public static String directionNAME_8d[] = { "HF.", "VU.", "HB.", "VD.", "SE.", "NE.", "NW.", "SW." };
    // public static int board_8d_row = 3;
    // public static int board_8d_col = 3;
    // public static boolean[][] visited_8d = new boolean[board_8d_row][board_8d_col];
    // FOR SHORTEST PATH.. CHANGE MIN_VALUE TO MAX_VALUE and if(condition --  make > <)
    public static boolean isValid_8d_shortest(Point point) {
        if(point.x < 0 || point.y < 0) {
            return false;
        } else if (point.x > board_8d_col - 1 || point.y > board_8d_row - 1) {
            return false;
        }
        return true;
    }
    public static Path getHeightOfShortestPath_8D(Point source, Point destination, int radius) {
        if(source.equals(destination)) {
            return new Path(0, "");
        }

        visited_8d[source.x][source.y] = true;
        Path local_path = new Path(Integer.MIN_VALUE, "");
        for(int i = 0; i < directions_8d.length; i++) {
            for(int j = 1; j <= radius; j++) {
                Point point = new Point(source.x + (j * directions_8d[i].x), source.y + (j * directions_8d[i].y));
                if(isValid_8d_shortest(point) && visited_8d[point.x][point.y] != true) {
                    Path temp_path = getHeightOfShortestPath_8D(point, destination, radius);
                    if(temp_path.len > local_path.len) {
                        local_path.len = temp_path.len;
                        local_path.name = temp_path.name + directionNAME_8d[i] + j;
                    }
                }
                 else { // UNDERSTAND THE LOGIC FOR THIS ELSE BLOCK
                    break;
                }
            }
        }
        visited_8d[source.x][source.y] = false;

        // if(local_path.len != Integer.MAX_VALUE)
        local_path.len = local_path.len + 1;

        return local_path;
    }

    // replace all if with > and change MAX_VALUE to MIN_VALUE
    public static Path getHeightOfShortestPath_HVD(Point source, Point destination, Path path) {
        if(source.equals(destination)) {
            return new Path(0, path.name);
        }

        Path local_path = new Path(Integer.MAX_VALUE, "");
        if(source.y < destination.y) {
            Point newHorizontal = new Point(source.x, source.y + 1);
            Path h_path = getHeightOfShortestPath_HVD(newHorizontal, destination, new Path(path.name + "H"));
            if(h_path.len < local_path.len) local_path = h_path;
        }

        if(source.x < destination.x && source.y < destination.y) {
            Point newDiagonal = new Point(source.x + 1, source.y + 1);
            Path d_path = getHeightOfShortestPath_HVD(newDiagonal, destination, new Path(path.name + "D"));
            if(d_path.len < local_path.len) local_path = d_path;
        }

        if(source.x < destination.x) {
            Point newVertical = new Point(source.x + 1, source.y);
            Path v_path = getHeightOfShortestPath_HVD(newVertical, destination, new Path(path.name + "V"));
            if(v_path.len < local_path.len) local_path = v_path;
        }

        local_path.len = local_path.len + 1;
        return local_path;
    }    

    public static Point directions_8d[] = { new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1),
            new Point(1, 1), new Point(-1, 1), new Point(-1, -1), new Point(1, -1) };
    public static String directionNAME_8d[] = { "HF.", "VU.", "HB.", "VD.", "SE.", "NE.", "NW.", "SW." };
    public static int board_8d_row = 3;
    public static int board_8d_col = 3;
    public static boolean[][] visited_8d = new boolean[board_8d_row][board_8d_col];

    public static boolean isValid_8d(Point source) {
        if (source.x > board_8d_col - 1 || source.y > board_8d_row - 1) {
            return false;
        } else if (source.x < 0 || source.y < 0) {
            return false;
        }
        return true;
    }

    public static int finderHV_UD_8d(Point source, Point destination, String answer, int radius) {
        if (source.equals(destination)) {
            // out.print("[" + answer + "],"); // uncomment to print all possible outcomes
            return 1;
        }

        int count = 0;
        visited_8d[source.x][source.y] = true;
        for (int i = 0; i < directionNAME_8d.length; i++) {
            for(int j = 1; j <= radius; j++) {
                Point newPoint = new Point(source.x + directions_8d[i].x * j, source.y + directions_8d[i].y * j);
                if (isValid_8d(newPoint) && !visited_8d[newPoint.x][newPoint.y]) {
                    count += finderHV_UD_8d(newPoint, destination, answer + j + directionNAME_8d[i], radius);
                }
            }
        }
        visited_8d[source.x][source.y] = false;
        return count;
    }

    public static Point directions[] = { new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1) };
    public static String directionNAME_4d[] = { "HF.", "VU.", "HB.", "VD." };
    public static int boardSize_4d_row = 3;
    public static int boardSize_4d_column = 3;
    public static boolean visited[][] = new boolean[boardSize_4d_row][boardSize_4d_column];

    public static boolean isValid_4d_multimove(Point source) {
        if (source.x > boardSize_4d_column - 1 || source.y > boardSize_4d_row - 1) {
            return false;
        } else if (source.x < 0 || source.y < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int finderHV_UD_multimove(Point source, Point destination, String answer, int radius) {
        if (source.equals(destination)) {
            // out.print("[" + answer + "],"); // uncomment to print all possible outcomes
            return 1;
        }
        int count = 0;
        visited[source.x][source.y] = true;
        for (int i = 0; i < directions.length; i++) {
            for (int j = 1; j <= radius; j++) {
                Point newPoint = new Point(source.x + (directions[i].x * j), source.y + (directions[i].y * j));
                if (isValid_4d_multimove(newPoint) && !visited[newPoint.x][newPoint.y]) {
                    count += finderHV_UD_multimove(newPoint, destination, answer + j + directionNAME_4d[i], radius);
                } else {
                    break;
                }
            }
        }
        visited[source.x][source.y] = false;
        return count;
    }

    // need to take care for path not tracing itself again
    // if asks for board lying in multiple quadrants, Moves will always be same.
    // however, in base condition...
    // add starting position to source....

    // declared above, here only for reference.
    // public static Point directions[] = {new Point(1, 0), new Point(0, 1), new
    // Point(-1, 0), new Point(0, -1)};
    // public static String directionNAME_4d[] = {"HF.", "VU.", "HB.", "VD."};
    // public static int boardSize_4d = 3;
    // public static boolean visited[][] = new boolean[boardSize_4d][boardSize_4d];
    public static boolean isValid_4d(Point source) {
        if (source.x > boardSize_4d_column - 1 || source.y > boardSize_4d_row - 1 || source.x < 0 || source.y < 0
                || visited[source.x][source.y]) {
            return false;
        }
        return true;
    }

    public static int finderHV_UD(Point source, Point dest, String answer) {
        if (source.equals(dest)) {
            out.print("[" + answer + "],");
            return 1;
        }
        int count = 0;
        visited[source.x][source.y] = true;
        for (int i = 0; i < directions.length; i++) {
            Point current = new Point(directions[i].x + source.x, directions[i].y + source.y);
            if (isValid_4d(current)) {
                count += finderHV_UD(current, dest, answer + directionNAME_4d[i]);
            }
        }
        visited[source.x][source.y] = false;
        return count;
    }

    public static int finderHVD_forward_down_diagonal_multimove(Point source, Point dest, String answer) {
        if (source.equals(dest)) {
            out.print(answer + ", ");
            return 1;
        }
        int count = 0;
        int times = Math.max(dest.x - source.x, dest.y - source.y);
        for (int i = 1; i <= times; i++) {
            if (source.x < dest.x) { // if horizontal move allowed
                Point newHorizontal = new Point(source.x + i, source.y);
                count += finderHVD_forward_down_diagonal_multimove(newHorizontal, dest, answer + "H" + i);
            }
            if (source.x < dest.x && source.y < dest.y) { // if diagonal move allowed
                Point newDiagonal = new Point(source.x + i, source.y + i);
                count += finderHVD_forward_down_diagonal_multimove(newDiagonal, dest, answer + "D" + i);
            }
            if (source.y < dest.y) { // if vertical move allowed
                Point newVertical = new Point(source.x, source.y + i);
                count += finderHVD_forward_down_diagonal_multimove(newVertical, dest, answer + "V" + i);
            }
        }
        return count;
    }

    public static int finderHVD_forward_down_diagonal(Point source, Point dest, String answer) {
        if (source.equals(dest)) {
            out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        if (source.x <= dest.x && source.y <= dest.y) {
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
        if (source.equals(dest)) {
            out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        if (source.x <= dest.x && source.y <= dest.y) {
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
        out.println(String.format("%" + fill + "s", " ").replaceAll(" ", "=") + " : " + word + " : "
                + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "="));
    }

    public static void printNewLine() {
        out.println();
    }

}