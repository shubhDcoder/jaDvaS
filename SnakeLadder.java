import static java.lang.System.out;

public class SnakeLadder {
    public static final int FIXED_LEN = 106;

    public static void main(String args[]) {
        printLine("SNAKE and LADDER");
        printLine();

        printLine("How many ways i can get dest, if i roll a dice..");
        out.print("\nnumber of ways are = > " + rolling_dice_waysToGet_dest("", 1, 5));

        printLine("How many ways i can get dest, if i roll a dice.. with 1 , 6 opening..");
        out.print("\nnumber of ways are = > " + rolling_dice_waysToGet_dest_1_6_opening("", 0, 5));

        printLine("How many ways i can get dest, if i roll a dice.. with 1 , 6 opening.. LADDER GAME.");
        out.print("\nnumber of ways are = > " + ladderGame("", 0, 6));

        printLine("Where i will if i follow a history of moves in SNAKE LADDER GAME.");
        snakeAndLadder(0, 20, 0);
    }

    public static int[] LADDER1 = {0, 0, 0, 10, 0, 0, 0, 0, 2, 0, 0, 17, 0, 0, 0, 5, 0, 0, 0, 0, 0};
    public static int[] moves = {2, 5, 3, 6, 1, 4, 2, 6, 1, 3, 2, 6, 6, 1}; // lose case
    public static int[] moves1 = {2, 5, 3, 6, 1, 4, 2, 6, 1, 3, 2, 6, 3, 1}; // win case
    
    public static void snakeAndLadder(int source, int destination, int index) {
        if(source == destination) {
            out.print("WON at index -> " + index + " having dice value " + moves[index]);
            return;
        }

        if(index >= moves.length) {
            out.print("STUCK at " + source);
            return;
        }

        if(source == 0) {
            if(moves[index] == 1 || moves[index] == 6) {
                snakeAndLadder(1, destination, index + 1);
            } else {
                snakeAndLadder(source, destination, index + 1);
            }
        } else {
            if(LADDER1[source] != 0) {
                out.println("coming from " + moves[index - 1] + " going from " + source + " to " + LADDER1[source]);
                snakeAndLadder(LADDER1[source], destination, index);
            } else {
                if(source + moves[index] > destination) {
                    snakeAndLadder(source, destination, index + 1);    
                } else {
                    snakeAndLadder(source + moves[index], destination, index + 1);
                }
            }
        }
    }

    public static final int[] LADDER = {0, 0, 5, 0, 8, 0};
    public static int ladderGame(String answer, int source, int destination) {
        if(source == destination) {
            out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        if(source == 0) {
            count += ladderGame("1", 1, destination);
            count += ladderGame("6", 1, destination);
        } else {
            for(int dice = 1; dice <= 6; dice++) {
                int intermediate = dice + source;
                if(intermediate > destination) break;
                int temp = LADDER[intermediate - 1];
                if(temp != 0) {
                    count += ladderGame(answer + "["+ dice + ":" + intermediate  + " > " + temp + "]", temp, destination);
                } else {
                    count += ladderGame(answer + dice, intermediate, destination);
                }
            }
        }

        return count;
    }

    public static int rolling_dice_waysToGet_dest_1_6_opening(String answer, int dice, int expect) {
        if(dice == expect) {
            out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        if(dice == 0) {
            count += rolling_dice_waysToGet_dest_1_6_opening(answer + 1, 1, expect);
            count += rolling_dice_waysToGet_dest_1_6_opening(answer + 6, 1, expect);
        }
        else {
            for(int i = 1; i <= 6; i++) {
                // proactive base case.. prevent faulty call to occur.
                if(i + dice > expect) break;
                count += rolling_dice_waysToGet_dest_1_6_opening(answer + i, dice + i, expect);
            }
        }

        return count;
    }

    public static int rolling_dice_waysToGet_dest(String answer, int dice, int expect) {
        if(dice == expect) {
            out.print(answer + ", ");
            return 1;
        }

        // reactive base case, when fault occurs, we handle..
        // if(dice > expect) return 0;

        int count = 0;
        for(int i = 1; i <= 6; i++) {
            // proactive base case.. prevent faulty call to occur.
            if(i + dice > expect) break;
            count += rolling_dice_waysToGet_dest(answer + i, dice + i, expect);
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