public class PrintUtil {
    // random utility methods
    public static final int FIXED_LEN = 135;
    public static void printLine() {
        System.out.println(String.format("%140s", " ").replaceAll(" ", "*"));
    }

    public static void printLine(String word) {
        printNewLine();
        int write = word.length();
        int fill = (FIXED_LEN - write) / 2;
        System.out.println(String.format("%" + fill + "s", " ").replaceAll(" ", "*") + " : " + word + " : "
                + String.format(String.format("%" + fill + "s", " ")).replaceAll(" ", "*"));
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void syso(Object obj) {
        System.out.println(obj);
    }

    public static void markHeading(String string) {
        printLine();
        syso(string);
        printLine();
    }
}