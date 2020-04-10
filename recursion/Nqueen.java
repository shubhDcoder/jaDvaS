public class Nqueen {

    public static final int BOARD_SIZE = 5;
    public static void main(String args[]) {
        printLine("N Queen problem -- start with basics");
        printLine();


        int[] array = new int[BOARD_SIZE];
        printLine("place N queens in a single dimensional array -- combination");
        syso("\nnumber of ways N queens can be placed in a single dimensional array without arranging are : " + 
            placeQueen_combination_01(array, 0, 3, 0, ""));
        printLine("place N queens in a single dimensional array -- permutaiton");
        syso("\nnumber of ways N queens can be placed in a single dimensional array with arrangements are : " + 
            placeQueen_permutation_01(array, 0, 3, 0, ""));

        syso(countCalls);
    }

    // public static int placeQueen_combination_02(int[] array, int placed, int totalQueen, int index, String answer) {
    //     if(place == totalQueen) {
    //         syso(answer + ", ");
    //         return 1;
    //     }

    //     if(index == array.length) {
    //         return 0;
    //     }

    //     int count = 0;
    //     if((totalQueen - placed) <= (array.length - index)) {
    //         for(int i = index; i < array.length; i++) {
    //             count += placeQueen_combination_02(array, placed + 1, totalQueen, index + 1, ans)
    //         }
    //     }
    // }

    public static boolean[] visited = new boolean[BOARD_SIZE];
    public static int placeQueen_permutation_01(int[] array, int placed, int totalQueen, int index, String answer) {
        countCalls++;
        if(totalQueen == placed) {
            syso(answer + ", ");
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
            syso(answer + ", ");
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
}