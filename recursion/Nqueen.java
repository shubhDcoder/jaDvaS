public class Nqueen {

  public static final int BOARD_SIZE = 16;
  public static final int BOARD_ROW_SIZE = 4;
  public static final int BOARD_COL_SIZE = 4;
  public static final int BOARD_2D_SIZE = BOARD_COL_SIZE * BOARD_ROW_SIZE;
  public static final boolean[][] VISITED_2D = new boolean[BOARD_ROW_SIZE][BOARD_COL_SIZE];

  public static void main(String args[]) {
    printLine("N Queen problem -- start with basics");
    printLine();

    markHeading("for one dimensional array == >");

    int[] array = new int[BOARD_SIZE];
    // int[][] array_2d = new int[BOARD_ROW_SIZE][BOARD_COL_SIZE];
    printLine("place N queens in a single dimensional array -- combination 0/1 METHOD");
    syso("\nresult without arranging are : " + placeQueen_combination_01(array, 0, 4, 0, ""));

    printLine("place N queens in a single dimensional array -- permutaiton 0/1 METHOD");
    syso("\nresult with arrangements are : " + placeQueen_permutation_01(array, 0, 4, 0, ""));

    printLine("place N queens in a single dimensional array -- COMBINATION - LOOP METHOD");
    syso("\nnresult without arrangements are : " + placeQueen_combination_02(array, 0, 4, 0, ""));

    printLine("place N queens in a single dimensional array -- PERMUTATION - LOOP METHOD");
    syso("\nresult WITH arrangements are : " + placeQueen_permutation_02(array, 0, 4, ""));

    markHeading("for two dimensional array == >");

    printLine("place N queens in a 2d array - COMBINATION - LOOP METHOD");
    syso("\nresult without arrangement are : " + placeQueen_combination_2d_01(4, 0, 0, ""));

    printLine("place N queens in a 2d array - Permutation - LOOP METHOD");
    syso("\nresult with arrangements are : " + placeQueen_permutation_2d_01(4, 0, ""));

    printLine("place N queens in a 2d array - COMBINATION - 0/1 METHOD");
    syso("\nresult without arrangements are : " + placeQueen_combination_2d_02(4, 0, "", 0));

    printLine("place N queens in a 2d array - PERMUTATION - 0/1 METHOD");
    syso("\nresult with arrangements are : " + placeQueen_permutation_2d_02(4, 0, "", 0));

    printLine(
        "place N queens in a 2d array - where no queen intersect each other WITH DIRECTION VECTOR");
    syso(
        "\nresult using direction vector other are "
            + placeQueen_combination_2d_01_basic(0, 4, 0, ""));

    printLine(
        "place N queens in a 2d array - where no queen intersect each other with o(1) complexity");
    syso(
        "\nresult using four boolean arrays other are "
            + placeQueen_combination_2d_01_adv(0, 4, 0, ""));

    printLine(
        "place N queens in a 2d array - where no queen intersect each other with o(1) complexity"
            + " USING BITS");
    syso("\nresult using bits are : " + placeQueen_combination_2d_01_adv_usingBits(0, 0, ""));
    resetBitMarker();

    countCalls = 0;
    printLine(
        "place N queens in 2d array where no queen intersect each other with o(1) using bits & 0/1"
            + " method");
    syso(
        "\nCombination : result using bits and 0/1 method are : "
            + placeQueen_combination_2d_02_adv_usingBits(0, 0, ""));
    syso("total calls are : " + countCalls);

    printLine(
        "place N queens in 2d array where no queen intersect each other with o(1) using bits & 0/1"
            + " method");
    syso(
        "\nPermutation : result using bits and 0/1 method are : "
            + placeQueen_permutation_2d_02_adv_usingBits(0, 0, ""));
    resetBitMarker();

    countCalls = 0;
    printLine(
        "place N queens in 2d array where no queen intersect each other with o(1) using bits & 0/1"
            + " method Best Complexity(COl power queen)");
    syso(
        "\nMOST OPTIMIZED WAY .. count is : "
            + placeQueen_combination_2d_01_adv_usingBits_final(0, 0, ""));
    syso("total calls are : " + countCalls);
    // syso(countCalls);
  }

  public static int rowVisitedBit = 0;
  public static int colVisitedBit = 0;
  public static int diagVisitedBit = 0;
  public static int aDiagvisitedBit = 0;
  public static int totalQueensForBit = 4;
  public static int countCalls = 0;

  public static void resetBitMarker() {
    rowVisitedBit = 0;
    colVisitedBit = 0;
    diagVisitedBit = 0;
    aDiagvisitedBit = 0;
  }

  public static int placeQueen_combination_2d_01_adv_usingBits_final(
      int row, int placed, String answer) {
    if (placed == totalQueensForBit) {
      syso(answer);
      return 1;
    }

    if (row == BOARD_ROW_SIZE) return 0;

    int count = 0;
    countCalls++;
    for (int col = 0; col < BOARD_COL_SIZE; col++) {
      int maskR = (1 << col);
      int maskC = (1 << row);
      int maskD = (1 << (row + col));
      int maskAD = (1 << (row - col + BOARD_COL_SIZE - 1));

      if ((maskR & colVisitedBit) == 0
          && (maskC & rowVisitedBit) == 0
          && (maskD & diagVisitedBit) == 0
          && (maskAD & aDiagvisitedBit) == 0) {
        rowVisitedBit ^= maskC;
        colVisitedBit ^= maskR;
        diagVisitedBit ^= maskD;
        aDiagvisitedBit ^= maskAD;

        count +=
            placeQueen_combination_2d_01_adv_usingBits_final(
                row + 1, placed + 1, answer + "Q" + placed + "(" + row + " " + col + ") ");

        rowVisitedBit ^= maskC;
        colVisitedBit ^= maskR;
        diagVisitedBit ^= maskD;
        aDiagvisitedBit ^= maskAD;
      }
    }

    return count;
  }

  public static int placeQueen_permutation_2d_02_adv_usingBits(
      int index, int placed, String answer) {
    if (placed == totalQueensForBit) {
      // syso(answer);
      return 1;
    }

    if (index == BOARD_2D_SIZE) return 0;

    int row = index / BOARD_COL_SIZE;
    int col = index % BOARD_COL_SIZE;
    int maskR = (1 << col);
    int maskC = (1 << row);
    int maskD = (1 << (row + col));
    int maskAD = (1 << (row - col + BOARD_COL_SIZE - 1));

    int count = 0;
    if ((maskR & colVisitedBit) == 0
        && (maskC & rowVisitedBit) == 0
        && (maskD & diagVisitedBit) == 0
        && (maskAD & aDiagvisitedBit) == 0) {
      rowVisitedBit ^= maskC;
      colVisitedBit ^= maskR;
      diagVisitedBit ^= maskD;
      aDiagvisitedBit ^= maskAD;

      count +=
          placeQueen_permutation_2d_02_adv_usingBits(
              0, placed + 1, answer + "Q" + placed + "(" + row + " " + col + ") ");

      rowVisitedBit ^= maskC;
      colVisitedBit ^= maskR;
      diagVisitedBit ^= maskD;
      aDiagvisitedBit ^= maskAD;
    }

    count += placeQueen_permutation_2d_02_adv_usingBits(index + 1, placed, answer);

    return count;
  }

  public static int placeQueen_combination_2d_02_adv_usingBits(
      int index, int placed, String answer) {
    if (totalQueensForBit == placed) {
      syso(answer);
      return 1;
    }

    if (index == BOARD_SIZE) {
      return 0;
    }

    int count = 0;
    countCalls++;
    if ((totalQueensForBit - placed) <= (BOARD_2D_SIZE - index)) {
      int row = index / BOARD_COL_SIZE;
      int col = index % BOARD_COL_SIZE;

      int maskR = (1 << col);
      int maskC = (1 << row);
      int maskD = (1 << (row + col));
      int maskAD = (1 << (row - col + BOARD_SIZE - 1));

      if ((maskR & colVisitedBit) == 0
          && (maskC & rowVisitedBit) == 0
          && (maskD & diagVisitedBit) == 0
          && (maskAD & aDiagvisitedBit) == 0) {
        colVisitedBit ^= maskR;
        rowVisitedBit ^= maskC;
        diagVisitedBit ^= maskD;
        aDiagvisitedBit ^= maskAD;

        count +=
            placeQueen_combination_2d_02_adv_usingBits(
                index + 1, placed + 1, answer + "Q" + placed + "(" + row + " " + col + ") ");

        colVisitedBit ^= maskR;
        rowVisitedBit ^= maskC;
        diagVisitedBit ^= maskD;
        aDiagvisitedBit ^= maskAD;
      }

      count += placeQueen_combination_2d_02_adv_usingBits(index + 1, placed, answer);
    }
    return count;
  }

  public static int placeQueen_combination_2d_01_adv_usingBits(
      int index, int placed, String answer) {
    if (placed == totalQueensForBit) {
      syso(answer);
      return 1;
    }

    int count = 0;
    for (int i = index; i < BOARD_2D_SIZE; i++) {
      int row = i / BOARD_COL_SIZE;
      int col = i % BOARD_COL_SIZE;

      int maskR = (1 << col);
      int maskC = (1 << row);
      int maskD = (1 << (row + col));
      int maskAD = (1 << (row - col + BOARD_COL_SIZE - 1));

      if ((maskR & colVisitedBit) == 0
          && (maskC & rowVisitedBit) == 0
          && (maskD & diagVisitedBit) == 0
          && (maskAD & aDiagvisitedBit) == 0) {
        rowVisitedBit ^= maskC;
        colVisitedBit ^= maskR;
        diagVisitedBit ^= maskD;
        aDiagvisitedBit ^= maskAD;
        count +=
            placeQueen_combination_2d_01_adv_usingBits(
                i + 1, placed + 1, answer + "Q" + placed + "(" + row + " " + col + ") ");
        rowVisitedBit ^= maskC;
        colVisitedBit ^= maskR;
        diagVisitedBit ^= maskD;
        aDiagvisitedBit ^= maskAD;
      }
    }

    return count;
  }

  public static boolean[] rowVisited = new boolean[BOARD_ROW_SIZE];
  public static boolean[] colVisited = new boolean[BOARD_COL_SIZE];
  public static boolean[] digVisitedF = new boolean[BOARD_ROW_SIZE + BOARD_COL_SIZE - 1];
  public static boolean[] digVisitedB = new boolean[BOARD_ROW_SIZE + BOARD_COL_SIZE - 1];

  public static boolean placeQueen_combination_2d_01_adv_isValid(int index) {
    int r = index / BOARD_COL_SIZE;
    int c = index % BOARD_COL_SIZE;

    if (rowVisited[r]
        || colVisited[c]
        || digVisitedF[r + c]
        || digVisitedB[r - c + BOARD_COL_SIZE - 1]) return false;

    return true;
  }

  public static int placeQueen_combination_2d_01_adv(
      int placed, int totalQueen, int index, String answer) {
    if (totalQueen == placed) {
      syso(answer);
      return 1;
    }

    int count = 0;
    if ((totalQueen - placed) <= (BOARD_2D_SIZE - index)) {
      for (int i = index; i < BOARD_2D_SIZE; i++) {
        if (placeQueen_combination_2d_01_adv_isValid(i)) {
          int r = i / BOARD_COL_SIZE;
          int c = i % BOARD_COL_SIZE;

          rowVisited[r] = true;
          colVisited[c] = true;
          digVisitedF[r + c] = true;
          digVisitedB[r - c + BOARD_COL_SIZE - 1] = true;
          count +=
              placeQueen_combination_2d_01_adv(
                  placed + 1, totalQueen, i + 1, answer + "Q" + placed + "(" + r + " " + c + ") ");
          rowVisited[r] = false;
          colVisited[c] = false;
          digVisitedF[r + c] = false;
          digVisitedB[r - c + BOARD_COL_SIZE - 1] = false;
        }
      }
    }

    return count;
  }

  public static int[][] directions = {{0, -1}, {-1, 0}, {-1, -1}, {-1, 1}};

  public static boolean placeQueen_combination_2d_01_basic_isValid(int index) {
    int row = index / BOARD_COL_SIZE;
    int col = index % BOARD_COL_SIZE;
    for (int[] dir : directions) {
      for (int i = 0; i < BOARD_COL_SIZE; i++) {
        int r = row + (i * dir[0]);
        int c = col + (i * dir[1]);
        if (r >= 0 && c >= 0 && r < BOARD_ROW_SIZE && c < BOARD_COL_SIZE) {
          if (VISITED_2D[r][c]) return false;
        } else break;
      }
    }
    return true;
  }

  public static int placeQueen_combination_2d_01_basic(
      int placed, int totalQueen, int index, String answer) {
    if (totalQueen == placed) {
      syso(answer);
      return 1;
    }

    int count = 0;
    if ((totalQueen - placed) <= (BOARD_2D_SIZE - index)) {
      for (int i = index; i < BOARD_2D_SIZE; i++) {
        if (placeQueen_combination_2d_01_basic_isValid(i)) {
          int x = i / BOARD_COL_SIZE;
          int y = i % BOARD_COL_SIZE;
          VISITED_2D[x][y] = true;
          count +=
              placeQueen_combination_2d_01_basic(
                  placed + 1, totalQueen, i + 1, answer + "Q" + placed + "(" + x + " " + y + ") ");
          VISITED_2D[x][y] = false;
        }
      }
    }
    return count;
  }

  public static boolean[] permutation_2d_02 = new boolean[BOARD_2D_SIZE];

  public static int placeQueen_permutation_2d_02(
      int totalQueen, int placed, String answer, int index) {
    if (placed == totalQueen) {
      // syso(answer);
      return 1;
    }

    if (index == BOARD_2D_SIZE) return 0;

    int count = 0;
    int x = index / 2;
    int y = index % 2;
    if (permutation_2d_02[index] == false) {
      permutation_2d_02[index] = true;
      count +=
          placeQueen_permutation_2d_02(
              totalQueen, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ", 0);
      permutation_2d_02[index] = false;
    }
    count += placeQueen_permutation_2d_02(totalQueen, placed, answer, index + 1);

    return count;
  }

  public static int placeQueen_combination_2d_02(
      int totalQueen, int placed, String answer, int index) {
    if (placed == totalQueen) {
      // syso(answer);
      return 1;
    }

    if ((totalQueen - placed) > (BOARD_2D_SIZE - index)) return 0;

    int count = 0;
    int x = index / BOARD_COL_SIZE;
    int y = index % BOARD_COL_SIZE;
    count +=
        placeQueen_combination_2d_02(
            totalQueen, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ", index + 1);
    count += placeQueen_combination_2d_02(totalQueen, placed, answer, index + 1);

    return count;
  }

  public static boolean[] permutation_2d_01 = new boolean[BOARD_2D_SIZE];

  public static int placeQueen_permutation_2d_01(int totalQueen, int placed, String answer) {
    if (totalQueen == placed) {
      // syso(answer);
      return 1;
    }

    int count = 0;
    for (int i = 0; i < BOARD_2D_SIZE; i++) {
      if (permutation_2d_01[i] == false) {
        int x = i / BOARD_ROW_SIZE;
        int y = i % BOARD_COL_SIZE;
        permutation_2d_01[i] = true;
        count +=
            placeQueen_permutation_2d_01(
                totalQueen, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ");
        permutation_2d_01[i] = false;
      }
    }

    return count;
  }

  public static int placeQueen_combination_2d_01(
      int totalQueen, int index, int placed, String answer) {
    if (totalQueen == placed) {
      // syso(answer);
      return 1;
    }

    // if(index == BOARD_2D_SIZE) return 0;

    int count = 0;
    for (int i = index; i < BOARD_2D_SIZE; i++) {
      if ((totalQueen - placed) <= (BOARD_2D_SIZE - index)) {
        int x = i / BOARD_COL_SIZE;
        int y = i % BOARD_COL_SIZE;
        count +=
            placeQueen_combination_2d_01(
                totalQueen, i + 1, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ");
      }
    }
    return count;
  }

  // FOR ONE DIMENSIONAL ARRAY -- JUST TO LAY FOUNDATION FOR REAL N QUEEN PROBLEM

  public static boolean[] combination_02 = new boolean[BOARD_SIZE];

  public static int placeQueen_permutation_02(
      int[] array, int placed, int totalQueen, String answer) {
    if (totalQueen == placed) {
      // syso(answer);
      return 1;
    }

    int count = 0;
    for (int i = 0; i < array.length; i++) {
      if (combination_02[i] == false) {
        combination_02[i] = true;
        count +=
            placeQueen_permutation_02(
                array, placed + 1, totalQueen, answer + "Q" + placed + "B" + i + ", ");
        combination_02[i] = false;
      }
    }

    return count;
  }

  public static int placeQueen_combination_02(
      int[] array, int placed, int totalQueen, int index, String answer) {
    if (placed == totalQueen) {
      // syso(answer + ", ");
      return 1;
    }

    // if(index == array.length) {
    //     return 0;
    // }

    int count = 0;
    if ((totalQueen - placed) <= (array.length - index)) {
      for (int i = index; i < array.length; i++) {
        count +=
            placeQueen_combination_02(
                array, placed + 1, totalQueen, i + 1, answer + "Q" + placed + "B" + i + " ");
      }
    }

    return count;
  }

  public static boolean[] visited = new boolean[BOARD_SIZE];

  public static int placeQueen_permutation_01(
      int[] array, int placed, int totalQueen, int index, String answer) {
    countCalls++;
    if (totalQueen == placed) {
      // syso(answer + ", ");
      return 1;
    }

    if (index == array.length) {
      return 0;
    }

    int count = 0;
    if (visited[index] == false) {
      visited[index] = true;
      count +=
          placeQueen_permutation_01(
              array, placed + 1, totalQueen, 0, answer + "Q" + placed + "B" + index + " ");
      visited[index] = false;
    }
    count += placeQueen_permutation_01(array, placed, totalQueen, index + 1, answer);

    return count;
  }

  public static int placeQueen_combination_01(
      int[] array, int placed, int totalQueen, int index, String answer) {
    // countCalls++;
    if (placed == totalQueen) {
      // syso(answer + ", ");
      return 1;
    }

    // if(index == array.length) {
    //     return 0;
    // }

    int count = 0;
    if (((totalQueen - placed) <= (array.length - index))) {
      if (totalQueen > placed) {
        count +=
            placeQueen_combination_01(
                array,
                placed + 1,
                totalQueen,
                index + 1,
                answer + "Q" + placed + "B" + index + " ");
      }
      count += placeQueen_combination_01(array, placed, totalQueen, index + 1, answer);
    }

    return count;
  }

  // random utility methods
  public static final int FIXED_LEN = 135;

  public static void printLine() {
    System.out.println(String.format("%140s", " ").replaceAll(" ", "*"));
  }

  public static void printLine(String word) {
    printNewLine();
    int write = word.length();
    int fill = (FIXED_LEN - write) / 2;
    System.out.println(
        String.format("%" + fill + "s", " ").replaceAll(" ", "*")
            + " : "
            + word
            + " : "
            + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "*"));
  }

  public static void printNewLine() {
    System.out.println();
  }

  public static void syso(Object obj) {
    System.out.println(obj);
  }

  public static void markHeading(String string) {
    printNewLine();
    printLine();
    syso(string);
    printLine();
    printNewLine();
  }
}
