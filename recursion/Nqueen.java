public class Nqueen {

    public static final int BOARD_SIZE = 16;
    public static final int BOARD_ROW_SIZE = 4;
    public static final int BOARD_COL_SIZE = 4;
    public static final int BOARD_2D_SIZE = BOARD_COL_SIZE * BOARD_ROW_SIZE;
    public static void main(String args[]) {
        printLine("N Queen problem -- start with basics");
        printLine();

        markHeading("for one dimensional array == >");

        int[] array = new int[BOARD_SIZE];
        int[][] array_2d = new int[BOARD_ROW_SIZE][BOARD_COL_SIZE];
        printLine("place N queens in a single dimensional array -- combination 0/1 METHOD");
        syso("\nnumber of ways N queens can be placed in a single dimensional array without arranging are : " + placeQueen_combination_01(array, 0, 4, 0, ""));
        
        printLine("place N queens in a single dimensional array -- permutaiton 0/1 METHOD");
        syso("\nnumber of ways N queens can be placed in a single dimensional array with arrangements are : " + placeQueen_permutation_01(array, 0, 4, 0, ""));
        
        printLine("place N queens in a single dimensional array -- COMBINATION - LOOP METHOD");
        syso("\nnumber of ways N queens can be placed in 1d array without arrangements are : " + placeQueen_combination_02(array, 0, 4, 0, ""));
        
        printLine("place N queens in a single dimensional array -- PERMUTATION - LOOP METHOD");
        syso("\nnumber of ways N queens can be placed in 1d array WITH arrangements are : " + placeQueen_permutation_02(array, 0, 4, ""));

        markHeading("for two dimensional array == >");

        printLine("place N queens in a 2d array - COMBINATION - LOOP METHOD");
        syso("\nnumber of ways N queens can be placed in 2d array without arrangement are : " + placeQueen_combination_2d_01(4, 0, 0, ""));

        printLine("place N queens in a 2d array - Permutation - LOOP METHOD");
        syso("\nnumber of ways N queens can be placed in 2d array with arrangements are : " + placeQueen_permutation_2d_01(4, 0, ""));

        printLine("place N queens in a 2d array - COMBINATION - 0/1 METHOD");
        syso("\nnumber of ways N queens can be placed in 2d array without arrangements are : " + placeQueen_combination_2d_02(4, 0, "", 0));

        printLine("place N queens in a 2d array - PERMUTATION - 0/1 METHOD");
        syso("\nnumber of ways N queens can be placed in 2d array with arrangements are : " + placeQueen_permutation_2d_02(4, 0, "", 0));

        // syso(countCalls);
    }

    public static boolean[] permutation_2d_02 = new boolean[BOARD_2D_SIZE];
    public static int placeQueen_permutation_2d_02(int totalQueen, int placed, String answer, int index) {
        if(placed == totalQueen) {
            // syso(answer);
            return 1;
        }

        if(index == BOARD_2D_SIZE) return 0;

        int count = 0;
        int x = index / 2;
        int y = index % 2;
        if(permutation_2d_02[index] == false) {
            permutation_2d_02[index] = true;
            count += placeQueen_permutation_2d_02(totalQueen, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ", 0);
            permutation_2d_02[index] = false;
        }
        count += placeQueen_permutation_2d_02(totalQueen, placed, answer, index + 1);

        return count;
    }

    public static int placeQueen_combination_2d_02(int totalQueen, int placed, String answer, int index) {
        if(placed == totalQueen) {
            // syso(answer);
            return 1;
        }

        if((totalQueen - placed) > (BOARD_2D_SIZE - index)) return 0;

        int count = 0;
        int x = index / 2;
        int y = index % 2;
        count += placeQueen_combination_2d_02(totalQueen, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ", index + 1);
        count += placeQueen_combination_2d_02(totalQueen, placed, answer, index + 1);

        return count;
    }

    public static boolean[] permutation_2d_01 = new boolean[BOARD_2D_SIZE];
    public static int placeQueen_permutation_2d_01(int totalQueen, int placed, String answer) {
        if(totalQueen == placed) {
            // syso(answer);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < BOARD_2D_SIZE; i++) {
            if(permutation_2d_01[i] == false) {
                int x = i / BOARD_ROW_SIZE;
                int y = i / BOARD_ROW_SIZE;
                permutation_2d_01[i] = true;
                count += placeQueen_permutation_2d_01(totalQueen, placed + 1, answer + "Q" + placed + "(" + x + " " + y + ") ");
                permutation_2d_01[i] = false;
            }
        }

        return count;
    }

    public static int placeQueen_combination_2d_01(int totalQueen, int index, int placed, String answer) {
        if(totalQueen == placed) {
            // syso(answer);
            return 1;
        }

        if(index == BOARD_2D_SIZE) return 0;

        int count = 0;
        for(int i = index; i < BOARD_2D_SIZE; i++) {
            if((totalQueen - placed) <= (BOARD_2D_SIZE - index)) {
                int x = i / BOARD_ROW_SIZE;
                int y = i % BOARD_ROW_SIZE;
                count += placeQueen_combination_2d_01(totalQueen, i + 1, placed + 1, answer + "Q" + placed + "(" + x + " "  + y + ") ");
            }
        }
        return count;
    }

    // FOR ONE DIMENSIONAL ARRAY -- JUST TO LAY FOUNDATION FOR REAL N QUEEN PROBLEM

    public static boolean[] combination_02 = new boolean[BOARD_SIZE];
    public static int placeQueen_permutation_02(int[] array, int placed, int totalQueen, String answer) {
        if(totalQueen == placed) {
            // syso(answer);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < array.length; i++) {
            if(combination_02[i] == false) {
                combination_02[i] = true;
                count += placeQueen_permutation_02(array, placed + 1, totalQueen, answer + "Q" + placed + "B" + i + ", ");
                combination_02[i] = false;
            }
        }

        return count;
    }
    
    public static int placeQueen_combination_02(int[] array, int placed, int totalQueen, int index, String answer) {
        if(placed == totalQueen) {
            // syso(answer + ", ");
            return 1;
        }

        if(index == array.length) {
            return 0;
        }

        int count = 0;
        if((totalQueen - placed) <= (array.length - index)) {
            for(int i = index; i < array.length; i++) {
                count += placeQueen_combination_02(array, placed + 1, totalQueen, i + 1, answer + "Q" + placed + "B" + i + " ");
            }
        }
        
        return count;
    }

    public static boolean[] visited = new boolean[BOARD_SIZE];
    public static int placeQueen_permutation_01(int[] array, int placed, int totalQueen, int index, String answer) {
        countCalls++;
        if(totalQueen == placed) {
            // syso(answer + ", ");
            return 1;
        }

        if(index == array.length) {
            return 0;
        }

        int count = 0;
        if(visited[index] == false) {
            visited[index] = true;
            count += placeQueen_permutation_01(array, placed + 1, totalQueen, 0, answer + "Q" + placed + "B" + index + " ");
            visited[index] = false;
        }
        count += placeQueen_permutation_01(array, placed, totalQueen, index + 1, answer);

        return count;
    }

    public static int countCalls = 0;
    public static int placeQueen_combination_01(int[] array, int placed, int totalQueen, int index, String answer) {
        // countCalls++;
        if(placed == totalQueen) {
            // syso(answer + ", ");
            return 1;
        }

        if(index == array.length) {
            return 0;
        }

        int count = 0;
        if(((totalQueen - placed) <= (array.length - index))) {
            if(totalQueen > placed) {
                count += placeQueen_combination_01(array, placed + 1, totalQueen, index + 1, answer + "Q" + placed + "B" + index + " ");
            }
            count += placeQueen_combination_01(array, placed, totalQueen, index + 1, answer);
        }

        return count;
    }

    // random utility methods
    public static final int FIXED_LEN = 106;
    public static void printLine() {
        System.out.println(String.format("%112s", " ").replaceAll(" ", "="));
    }

    public static void printLine(String word) {
        printNewLine();
        int write = word.length();
        int fill = (FIXED_LEN - write) / 2;
        System.out.println(String.format("%" + fill + "s", " ").replaceAll(" ", "=") + " : " + word + " : "
                + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "="));
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