public class BitManipulation {
    public static void main(String args[]) {
        printLine("maximizing XOR");
        syso("result => " + maximizingXOR(11, 100));

        printLine("SUM VS XOR");
        syso("result => " + sumXor(10l));

        printLine("flipping bits");
        syso("result => " + flippingBits(802743475l));
        
        printLine("XOR sequence");
        syso("result => " + xorSequence(3, 5));
        syso("result => " + xorSequence(4, 6));
        syso("result => " + xorSequence(15, 20));

        printLine("counter game");
        syso("result => " + counterGame(6l  ));

        printLine("the Great XOR");
        syso("result => " + theGreatXor(10000000000l));
    }

    public static long theGreatXor(long x) {
        long answer = 0;

        // runs at most 63 times for unsigned int
        for(long i = 0; i < Long.SIZE; i++) {
            if((x & 1) == 0) answer = (answer | (1l << i));
            x = (x >> 1);
            if(x == 0) break;
        }

        return answer;
    }

    public static String counterGame(long n) {
        n = n - 1;
        int count = 0;
        while(n != 0) {
            n = (n & (n - 1));
            count++;
        }
        return ((count & 1) == 1) ? "Louise" : "Richard";
    }

    public static long xorSequence(long left, long right) {
        long answer = 0;

        int left_r = (int)(left % 4l);
        long l_block_start = left + (4 - left_r);
        int right_R = (int)(right % 4l);
        long r_block_end = right - right_R;

        long no_of_blocks = (r_block_end - l_block_start) / 4l;
        if((no_of_blocks % 2) != 0) answer ^= 2;

        for(long i = left; i < l_block_start; i++) {
            long remainder = (i % 4l);
            if(remainder == 0) answer ^= i;
            else if(remainder == 1) answer ^= 1;
            else if(remainder == 2) answer ^= (i + 1);
        }

        for(long i = right; i >= r_block_end; i--) {
            long remainder = (i % 4l);
            if(remainder == 0) answer ^= i;
            else if(remainder == 1) answer ^= 1;
            else if(remainder == 2) answer ^= (i + 1);
        }
        return answer;
    }

    public static long getLess2power(long number) {
        number |= (number >> 1);
        number |= (number >> 2);
        number |= (number >> 4);
        number |= (number >> 16);
        number |= (number >> 32);

        number += 1;
        return number >>> 1;
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