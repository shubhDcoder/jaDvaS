import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class Recursion_001 {

    public static void main(String args[]) {
        int count = 10;
        printBlankLine();
        System.out.print("RECURSION CLASS");
        printBlankLine();
        printLineWithMessage("increasing order using recursion.\n");
        printInIncreasingOrder(0, count);
        printLineWithMessage("decreasing order using recursion.\n");
        printInDecreasingOrder(0, count);
        printLineWithMessage("up and down in recursion.\n");
        printUpAndDownOrder(0, count);
        printLineWithMessage("factorial using recursion.\n");
        System.out.print(factorial(6));
        printLineWithMessage("power of two.\n");
        System.out.print(power_v1(3, 4));
        printLineWithMessage("power of two Optimized.\n");
        System.out.print(power_v2(2, 9));
        printLineWithMessage("touch each node thrice. printPattern(5);\n");
        print(printPattern(5));
        printLineWithMessage("find value in array using recursion.\n");
        print("14 found : " + findValueUsingRecursion(new int[]{2, 5, 8, 10, 11}, 0, 14));
        printLineWithMessage("find Max value in array using recursion.\n");
        print("Max found : " + findMaxValueUsingRecursion(new int[]{23, 5, 8, 10, 11}, 0));
        printLineWithMessage("print array in forward direction.\n");
        displayArrayUsingRecursionStraight(new int[]{23, 5, 8, 10, 11}, 0);
        printLineWithMessage("print array in reverse direction.\n");
        displayArrayUsingRecursionReverse(new int[]{23, 5, 8, 10, 11}, 0);
        printLineWithMessage("find first occurance of element.\n");
        print("found at : " + printFirstIndexOfElement(new int[]{23, 5, 8, 10, 11}, 14, 0));
        printLineWithMessage("find last occurance of element.\n");
        print("last index found at : " + printLastIndexOfElement(new int[]{2, 8, 2, 9, 11, 2, 15}, 12, 0));
        printLineWithMessage("find all occurance of element.\n");
        System.out.print("last index found at : ");
        printAllIndexOfElement(new int[]{2, 8, 2, 9, 11, 2, 15}, 2, 0);
        printLineWithMessage("reversing string 'abcdefghij' \n");
        printReversedString("abcdefghij", 0);
        print(new String(stringReversedArray));
        printLineWithMessage("reversing string 'abcdefghij' USING STRINGBUILDER_ V2 \n");
        print(printReversedString_v2("abcdefghijklmnop", 0, new StringBuilder()).toString());
        printLineWithMessage("print a pattern... for fun\n");
        printPatternforStar(5);
        printLineWithMessage("Starting tower of hanoi for plates = 3 \n");
        startHanoi(3, "source", "intermediate", "destination");
        printLineWithMessage("find subsequences using VOID type -- one recursion call\n");
        print(findSubsequencesVoid_V1("abcde"));
        printLineWithMessage("find subsequences using INT return type -- two recursion call\n");
        print("\nsize is ==> " + findSubsequences_V2("", "abc"));
        printLineWithMessage("find subsequences using String ascii -- three choices for every char.\n");
        List<String> received1 = findSubsequencesStringAscii_V1("abc");
        print(received1);
        print("size is ==> " + received1.size());
        printLineWithMessage("find subsequences using String ascii -- three choices for every char Using 3 recursion.\n");
        print("\ntotal subsequences could be : " + findSubsequencesStringAscii_V2("", "abc"));
        printLineWithMessage("find permutations of string. using One recursion. \n");
        List<String> received2 = findPermutation_V1("abcd");
        print(received2);
        print("size is ==> " + received2.size());
        printLineWithMessage("find permutations of string. calls for each character.\n");
        print("\nsize is ==> " + findPermutation_V2("", "abcd"));
        printLineWithMessage("find permutations of string. Without duplicates.\n");
        print("\nsize is ==> " + findPermutation_V2_optimized_for_no_duplicates("", "aba"));
        printLineWithMessage("keypad V1 - Return type--> 567 \n");
        List<String> received3 = printAllKeyPadCombination_V1("567");
        print(received3 + "\nsize is ==> " + received3.size());
        printLineWithMessage("keypad V2 void type--> 567 \n");
        print("\nsize is = > " + printAllKeyPadCombination_V2("1011", ""));
        printLineWithMessage("keypad V2 void type--> 1011 - Advance..  \n");
        print("\nsize is = > " + printAllKeyPadCombination_V3("1011", ""));
        printLineWithMessage("keypad V2 void type--> 1020 - Advance.. Single elements for string.. \n");    
        print("\nsize is = > " + printAllKeyPadCombination_V4("", "10203415"));
    }

    public static final char[] MAPPING = {'a', 'b', 'c', 'd', 'e', 'f','g', 'h', 'i', 'j', 'k', 'l','m', 'n', 'o','p', 'q', 'r','s', 't', 'u','v', 'w', 'x','y', 'z'};
    public static int printAllKeyPadCombination_V4(String answer, String dialed) {
        if(dialed.length() == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        char c1 = dialed.charAt(0);
        if(c1 != '0') {
            char c0 = MAPPING[c1 - '0' - 1];
            String send = dialed.substring(1);
            count = count + printAllKeyPadCombination_V4(answer + c0, send);

            if(dialed.length() > 1) {
                int twoChars = Integer.parseInt(dialed.substring(0, 2));
                if(twoChars > 9 && twoChars < 27) {
                    char c2 = MAPPING[twoChars];
                    String sendAgain = dialed.substring(2);
                    count = count + printAllKeyPadCombination_V4(answer + c2, sendAgain);
                }
            }

        }
        return count;
    }

    public static final String[] KEYPAD = {"abc", "def", "ijk", "lmn", "opq", "rst", "uvw", "xyz", "!@#", "$%&", "*()", "~^+"};
    public static int printAllKeyPadCombination_V3(String dialed, String answer) {
        if(dialed.length() == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        char c1 = dialed.charAt(0);
        String send = dialed.substring(1);
        String willProcess = KEYPAD[c1 - '0'];
        int count = 0;
        for(int i = 0; i < willProcess.length(); i++) {
            char c2 = willProcess.charAt(i);
            count += printAllKeyPadCombination_V3(send, answer + c2);
        }

        if(dialed.length() > 1) {
            int twoLetters = Integer.parseInt(dialed.substring(0, 2));
            if(twoLetters <= 11 || twoLetters >= 10) {
                String willProcessAgain = KEYPAD[twoLetters];
                for(int i = 0; i < willProcessAgain.length(); i++) {
                    char c3 = willProcessAgain.charAt(i);
                    String sendAgain = dialed.substring(2);
                    count += printAllKeyPadCombination_V3(sendAgain, answer + c3);
                }
            }
        }

        return count;
    }

    // Below for reference : declared above 
    // public static final String[] KEYPAD = {"abc", "def", "ijk", "lmn", "opq", "rst", "uvw", "xyz", "!@#", "$%&", "*()", "~^+"};
    public static int printAllKeyPadCombination_V2(String dialed, String answer) {
        if(dialed.length() == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        char c1 = dialed.charAt(0);
        String send = dialed.substring(1);
        String willProcess = KEYPAD[c1 - '0'];
        int count = 0;
        for(int i = 0; i < willProcess.length(); i++) {
            char c2 = willProcess.charAt(i);
            count = count + printAllKeyPadCombination_V2(send, answer + c2);
        }

        return count;
    }

    // Below for reference : declared above 
    // public static final String[] KEYPAD = {"abc", "def", "ijk", "lmn", "opq", "rst", "uvw", "xyz", "!@#", "$%&", "*()", "~^+"};
    public static List<String> printAllKeyPadCombination_V1(String dialed) {
        if(dialed.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add("");
            return list;
        }

        char c = dialed.charAt(0);
        String send = dialed.substring(1);

        List<String> received = printAllKeyPadCombination_V1(send);
        List<String> local = new ArrayList<>();
        
        for(String s : received) {
            String temp = KEYPAD[c - '0'];
            for(int i = 0; i < temp.length(); i++) {
                local.add(temp.charAt(i) + s);
            }
        }

        return local;

    }
    
    public static int findPermutation_V2_optimized_for_no_duplicates(String answer, String forBlank) {
        if(forBlank.length() == 1) {
            System.out.print(answer + forBlank + ", ");
            return 1;
        }

        boolean[] mark = new boolean[26];

        int count = 0;
        for(int i = 0; i < forBlank.length(); i++) {            
            char c = forBlank.charAt(i);
            if(mark[c - 'a']) continue;
            String temp = forBlank.substring(0, i) + forBlank.substring(i + 1);
            mark[c - 'a'] = true;
            count = count + findPermutation_V2_optimized_for_no_duplicates(answer + c, temp);
        }
        return count;
    }

    public static int findPermutation_V2(String answer, String forBlank) { // do again for practice
        if(forBlank.length() == 1) {
            System.out.print(answer + forBlank + ", ");
            return 1;
        }

        int count = 0;
        for(int i = 0; i < forBlank.length(); i++) {
            String temp0 = answer + forBlank.charAt(i);
            String temp1 = forBlank.substring(0, i) + forBlank.substring(i + 1);
            count = count + findPermutation_V2(temp0, temp1);
        }

        return count;
    }

    public static List<String> findPermutation_V1(String string) {
        if(string.length() == 1) {
            List<String> list = new ArrayList<String>();
            list.add(string);
            return list;
        }

        char c = string.charAt(0);
        String send = string.substring(1);

        List<String> received_list = findPermutation_V1(send);
        List<String> local_list = new ArrayList<String>();

        for(String s : received_list) {
            for(int i = 0; i <= s.length(); i++) {
                String injected = s.substring(0, i) + c + s.substring(i);
                local_list.add(injected);
            }
        }

        return local_list;
    }

    public static int findSubsequencesStringAscii_V2(String answer, String forBlank) {
        if(forBlank.length() == 0) {
            System.out.print(answer + ", ");
            return 1;
        }

        int count = 0;
        char myChar = forBlank.charAt(0);
        String send = forBlank.substring(1);

        count = count + findSubsequencesStringAscii_V2(myChar + answer, send);
        count = count + findSubsequencesStringAscii_V2((int) myChar + answer, send);
        count = count + findSubsequencesStringAscii_V2(answer, send);

        return count;
    }

    public static List<String> findSubsequencesStringAscii_V1(String string) {
        if(string.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add("");
            return list;
        }

        char c = string.charAt(0);
        String send = string.substring(1);

        List<String> received_list = findSubsequencesStringAscii_V1(send);
        List<String> local_list = new ArrayList<>(received_list);

        for(String s : received_list) {
            local_list.add(c + s);
            local_list.add((int) c + s);
        }

        return local_list;
    }

    public static int findSubsequences_V2(String string, String forBlank) {
        if(forBlank.length() == 0) {
            System.out.print(string + ", ");
            return 1;
        }

        int count = 0;

        count = count + findSubsequences_V2(string + forBlank.charAt(0), forBlank.substring(1));
        count = count + findSubsequences_V2(string, forBlank.substring(1));
        
        return count;
    }


    public static List<String> findSubsequencesVoid_V1(String string) {
        if(string.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add("");
            return list;
        }

        List<String> list_received = findSubsequencesVoid_V1(string.substring(1));
        List<String> list_local = new ArrayList<>();
        list_local.addAll(list_received); // first choice -- not including self

        char localStr = string.charAt(0);
        for(String s : list_received) list_local.add(localStr + s); // second choice -- including self

        return list_local;
    }

    public static void startHanoi(int count, String source, String intermediate, String destination) {
        if(count == 1) {
            print("shifting plate number " + count + " from " + source + " to " + destination);
            return;
        }

        startHanoi(count - 1, source, destination, intermediate);
        print("shifting plate number " + count + " from " + source + " to " + destination);
        startHanoi(count - 1, intermediate, source, destination);
    }

    public static void printPatternforStar(int count) {
        for(int i = 0; i < count; i++) {
            for(int j = 0; j <= i; j++) {
                if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) System.out.printf("%-2s", "x");
                else System.out.printf("%-2s", " ");
            }
            printNewLine();
        }
    }
    
    public static char[] stringReversedArray = new char[10];
    public static void printReversedString(String string, int count) {
        if(count == string.length()) return;
        printReversedString(string, count + 1);
        stringReversedArray[string.length() - count - 1] = string.charAt(count);
    }

    public static StringBuilder printReversedString_v2(String string, int count, StringBuilder stringBuilder) {
        if(count < string.length()) {
            printReversedString_v2(string, count + 1, stringBuilder);
            stringBuilder.append(string.charAt(count));
        }
        return stringBuilder;
    }

    public static void printAllIndexOfElement(int[] array, int key, int index) {
        if(index == array.length) return; 
        if(array[index] == key) System.out.print(index + " ");
        printAllIndexOfElement(array, key, index + 1);
    }

    public static int printLastIndexOfElement(int[] array, int key, int index) {
        if(index == array.length - 1) return array[index] == key ? index : -1;

        int status = printLastIndexOfElement(array, key, index + 1);
        
        if(status == -1) return array[index] == key ?  index : -1;
        else return status;
    }

    public static int printFirstIndexOfElement(int[] array, int key, int index) {
        if(index == array.length) return -1;
        else if(array[index] == key) return index;
        else return printFirstIndexOfElement(array, key, index + 1);
    }

    public static void displayArrayUsingRecursionStraight(int[] array, int index) {
        if(index == array.length) return;
        System.out.print(array[index] + " ");
        displayArrayUsingRecursionStraight(array, index + 1);
    }

    public static void displayArrayUsingRecursionReverse(int[] array, int index) {
        if(index == array.length) return;
        displayArrayUsingRecursionReverse(array, index + 1);
        System.out.print(array[index] + " ");
    }

    public static int findMaxValueUsingRecursion(int[] array, int counter) {
        if(counter == array.length - 1) return array[counter];
        return Math.max(array[counter], findMaxValueUsingRecursion(array, counter + 1));
    }

    public static boolean findValueUsingRecursion(int[] array, int counter, int key) {
        if(counter == array.length) return false;

        if(array[counter] == key) return true;
        else return findValueUsingRecursion(array, counter + 1, key);
    }

    public static int printPattern(int number) {
        if(number <= 1) {
            System.out.print("base " + number + " ==> "); 
            return number;
        }
        int count = 0;

        System.out.print("pre " + number + " ==> ");

        printPattern(number - 1);

        System.out.print("in " + number + " ==> ");

        printPattern(number - 2);

        System.out.print("post " + number + " ==> ");

        return count + 3;
    }

    public static int power_v1(int base, int power) {
        if(power == 0) return 1;
        return base * power_v1(base, power - 1);
    }

    public static int power_v2(int base, int power) {
        if(power == 0) return 1;
        
        int calc = power_v2(base, power / 2);
        calc *= calc;

        return power % 2 == 0 ? calc : calc * base;
    }

    public static int factorial(int a) {
        if(a <= 1) return 1;
        return a * factorial(a - 1);
    }

    public static void printUpAndDownOrder(int a, int b) {
        if(a == b + 1) return;
        
        if(a % 2 == 0) System.out.printf("%-3d", a);
        printUpAndDownOrder(a + 1, b);
        if(a % 2 != 0) System.out.printf("%-3d", a);
    }

    public static void printInIncreasingOrder(int a, int b) {
        if(a == b + 1) return;
        System.out.printf("%-3d", a);
        printInIncreasingOrder(a + 1, b);
    }

    public static void printInDecreasingOrder(int a, int b) {
        if(a == b + 1) return;
        printInDecreasingOrder(a + 1, b);
        System.out.printf("%-3d", a);
    }

    public static void printBlankLine() {
        print(String.format("\n%100s", " ").replaceAll(" ", "="));
    }

    public static void printNewLine() {
        print("");
    }

    public static void print(Object message) {
        String string = message.toString();
        int len = string.length();
        if(len > 150) {
            int times = len % 150;
            for(int i = 0; i < times; i++) {
                int start = i * 150;
                System.out.println(string.substring(start, start + 150));
            }
        } else System.out.println(string);
    }

    public static void printLineWithMessage(String message) {
        print(String.format("\n%50s", " ").replaceAll(" ", "=") + "> " + message);
    }
}