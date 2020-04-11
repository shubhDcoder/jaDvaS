public class BitManipulation {
    public static void main(String args[]) {
        printLine("maximizing XOR");
        syso("result => " + maximizingXOR(11, 100));

        printLine("SUM VS XOR");
        syso("result => " + sumXor(10l));

        printLine("flipping bits");
        syso("result => " + flippingBits(802743475l));
        
    }

    public static long flippingBits(long n) {
        long answer = 0;

        for(int i = 0; i < Integer.SIZE; i++) {
            if((n & 1) == 1) {
                answer = (answer & (~(1l << i)));
            } else {
                answer = (answer | (1l << i));
            }
            n = n >> 1;
        }

        return answer;
    }

    public static long sumXor(long n) {
        
        int count = 0;
        while(n != 0) {
            if((n & 1) == 0) count++;
            n >>= 1;
        }

        // syso(count);
        // System.out.println(new Long(1 << count));
        return 1l << count;
    }

    public static int maximizingXOR(int l, int r) {
        // System.out.println(Integer.toBinaryString(l));
        // System.out.println(Integer.toBinaryString(r));
        // get the MSB 
        int index = 0;
        for(index = Integer.SIZE - 1; index >= 0; index--) {
            int mask = (1 << index);
            int left = (l & (1 << index));
            int right = (r & (1 << index));
            if((left ^ right) != 0) {
                break;
            }
        }

        // System.out.println(index);
        // int answer = 0;
        int answer = (1 << (index + 1)) - 1;
        // for(int i = 0; i <= index; i++) answer = answer | (1 << i);
        // System.out.println(answer);
        return answer;
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