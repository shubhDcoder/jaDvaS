public class CoinsChange01 {
    public static void main(String args[]) {
        printLine("coins problems using include/exclude technique");
        printLine();
        printNewLine();

        printLine("Combination limited supply");
        int[] array = {2, 3, 5, 7};
        System.out.println("\nnumber of combinatinos for target 10 and inputs (2, 3, 5, 7) are : " + getCombinationLimitedSupply(array, 0, 10, ""));

        printLine("Combination infinite supply");
        System.out.println("\nnumber of combinatinos(Infinite supply) for target 10 and inputs (2, 3, 5, 7) are : " + getCombinationInfiniteSupply(array, 0, 10, ""));

        printLine("Permutation infinite supply");
        System.out.println("\nnumber of permutations(Infinite supply) for target 10 and inputs (2, 3, 5, 7) are : " + getPermutationInfiniteSupply(array, 0, 10, ""));

        printLine("Permutation limited supply");
        System.out.println("\nnumber of permutation (Limited supply) for target 10 and inputs (2, 3, 5, 7) are : " + getPermutationLimitedSupply(array, 0, 10, ""));

    }

    public static boolean[] visited = new boolean[4];
    public static int getPermutationLimitedSupply(int array[], int index, int target, String answer) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }
        
        if(index == array.length) {
            return 0;
        }

        int count = 0;
        if(target - array[index] >= 0 && visited[index] == false) {
            visited[index] = true;
            count += getPermutationLimitedSupply(array, 0, target - array[index], answer + array[index]);
            visited[index] = false;
        }
        count += getPermutationLimitedSupply(array, index + 1, target, answer);

        return count;
    }

    public static int getPermutationInfiniteSupply(int[] array, int index, int target, String answer) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        if(index == array.length) {
            return 0;
        }
        
        int count = 0;
        if(target - array[index] >= 0) 
            count += getPermutationInfiniteSupply(array, 0, target - array[index], answer + array[index]);
        count += getPermutationInfiniteSupply(array, index + 1, target, answer);
        
        return count;
    }

    public static int getCombinationInfiniteSupply(int[] array, int index, int target, String answer) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        if(index == array.length) return 0;

        int count = 0;
        if(target >= array[index])
            count += getCombinationInfiniteSupply(array, index, target - array[index], answer + array[index]);
        
        count += getCombinationInfiniteSupply(array, index + 1, target, answer);

        return count;
    }

    public static int getCombinationLimitedSupply(int[] array, int index, int target, String answer) {
        if(target == 0) {
            System.out.print(answer + ", ");
            return 1;
        }
        if(index == array.length) return 0;

        int count = 0;
        if(target >= array[index])
            count += getCombinationLimitedSupply(array, index + 1, target - array[index], answer + array[index]);

        count += getCombinationLimitedSupply(array, index + 1, target, answer);

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