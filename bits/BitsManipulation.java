import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BitsManipulation {
    public static void main(String args[]) {
        printLine("BIT MANIPULATION");
        printLine();

        printLine("checking if an Ith bit is set or not");
        syso("result ==> " + checkIthBitIsSet(23, 3));
        printLine("set the ith bit from LSB");
        syso("result ==> " + setIthBit(47, 4));
        printLine("check if a number is power of two");
        syso("result ==> " + checkIfPowerOf2(64));
        printLine("clear ith bit");
        syso("result ==> " + clearIthBit(47, 3));
        printLine("every element in array is repeated two times except one. find that");
        syso("result ==> " + findHiddenElement_01(new int[] {2, 2, 3, 3, 5, 5, 7}));
        printLine("every element in array is repeated K times except one. find that");
        syso("result ==> " + findHiddenElement_02(new int[] {2, 2, 2, 3, 3, 3, 5, 5, 5, 9}, 3));
        printLine("every element in array is repeated 2 times except two. find those");
        findHiddenElement_03(new int[] {2, 2, 3, 3, 5, 5, 9, 13}, 2);
    }

    public static void findHiddenElement_03(int array[], int k) {
        syso(Arrays.toString(array));

        int combinded_number = 0;
        for(int i = 0; i < 32; i++) {
            int count = 0;
            for(int element : array) {
                if((element & (1 << i)) != 0) {
                    count++;
                }
            }
            if(count % k != 0) combinded_number |= (1 << i);
        }

        int firstChangedBitNumber = (combinded_number & (-combinded_number));

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        for(int element : array) {
            if((element & firstChangedBitNumber) != 0)
                listA.add(element);
            else 
                listB.add(element);
        }

        int numberA = 0;
        int numberB = 0;
        for(int element : listA) numberA ^= element;
        for(int element : listB) numberB ^= element;

        syso("numbers are => " + numberA + ", " + numberB);
    }


    public static int findHiddenElement_02(int array[], int k) {
        syso(Arrays.toString(array));
        int hiddenNumber = 0;
        for(int i = 0; i < Integer.SIZE; i++) {
            int count = 0;
            for(int element : array) {
                if((element & (1 << i)) != 0) count++;
            }
            if(count % k != 0) hiddenNumber |= (1 << i);
        }

        return hiddenNumber;
    }

    public static int findHiddenElement_01(int array[]) {
        syso(Arrays.toString(array));
        int number = array[0];
        for(int i = 1; i < array.length; i++) {
            number ^= array[i];
        }

        return number;
    }

    public static String clearIthBit(int number, int i) {
        syso("number => " + String.format("%8s", Integer.toBinaryString(number)).replaceAll(" ", "0") + " => " + number);
        int mask = (~(1 << i));
        syso(String.format("%8s", Integer.toBinaryString(mask)).replaceAll(" ", "0") + " => bit " + i);
        return Integer.toBinaryString(mask & number);
    }

    public static boolean checkIfPowerOf2(int number) {
        syso("number => " + String.format("%8s", Integer.toBinaryString(number)).replaceAll(" ", "0") + " => " + number);
        if(number == 0) return false;

        return (number & (number - 1)) == 0;
    }

    public static boolean checkIthBitIsSet(int number, int i) {
        syso(String.format("%8s", Integer.toBinaryString(number)).replaceAll(" ", "0") + " => " + number);
        syso(String.format("%8s", Integer.toBinaryString(1 << i)).replaceAll(" ", "0") + " => bit " + i);
        return (number & (1 << i)) != 0;
    }

    public static int setIthBit(int number, int i) {
        syso(String.format("%8s", Integer.toBinaryString(number)).replaceAll(" ", "0") + " => " + number);
        syso(String.format("%8s", Integer.toBinaryString(1 << i)).replaceAll(" ", "0") + " => bit " + i);
        return (number | (1 << i));
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
