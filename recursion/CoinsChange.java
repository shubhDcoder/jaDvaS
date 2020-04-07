public class CoinsChange {
    public static void main(String args[]) {
        printLine("coins problems");
        printLine();
        printNewLine();

        printLine("total permutatinos of (2,3,5,7 - INFINITE SUPPLY) with sum of 10 are : ");
        System.out.println("\nSize is : " + permutation_infinite_supply(new int[] {2, 3, 5, 7}, 10, ""));
        printLine("total combinations of (2,3,5,7 - INFINITE SUPPLY) with sum of 10 are : ");
        System.out.println("\nSize is : " + combination_infinite_supply(new int[] {2, 3, 5, 7}, 10, "", 0));
        printLine("total permutations of (2,3,5,7 - LIMITED SUPPLY) with sum of 10 are : ");
        System.out.println("\nSize is : " + permutation_limited_supply(new int[] {2, 3, 5, 7}, 10, ""));
        printLine("total combination of (2,3,5,7 - LIMITED SUPPLY) with sum of 10 are : ");
        System.out.println("\nSize is : " + combination_limited_supply(new int[] {2, 3, 5, 7}, 10, "", 0));
    }

    public static boolean[] visited_combination_limited_supply = new boolean[4];
    public static int combination_limited_supply(int[] array, int target, String answer, int index) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        for(int i = index; i < array.length; i++) {
            if(visited_combination_limited_supply[i] != true && target >= array[i]) {
                visited_combination_limited_supply[i] = true;
                count += combination_limited_supply(array, target - array[i], answer + array[i], i + 1);
                visited_combination_limited_supply[i] = false;
            }
        }
        return count;
    }

    public static boolean[] visited_permutation_limited_supply = new boolean[4];
    public static int permutation_limited_supply(int[] array, int target, String answer) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        for(int i = 0; i < array.length; i++) {
            if(visited_permutation_limited_supply[i] != true && target >= array[i]) {
                visited_permutation_limited_supply[i] = true;
                count += permutation_limited_supply(array, target - array[i], answer + array[i]);
                visited_permutation_limited_supply[i] = false;
            }
        }
        return count;
    }

    public static int combination_infinite_supply(int[] array, int target, String answer, int startIndex) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        for(int i = startIndex; i < array.length; i++) {
            if(target >= array[i]) {
                count += combination_infinite_supply(array, target - array[i], answer + array[i], i);
            }
        }
        return count;
    }

    public static int permutation_infinite_supply(int[] array, int target, String answer) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        for(int i = 0; i < array.length; i++) {
            if(target - array[i] >= 0) {
                count += permutation_infinite_supply(array, target - array[i], answer + array[i]);
            }
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
}