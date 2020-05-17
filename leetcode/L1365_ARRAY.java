import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class L1365_ARRAY {

    public static void main(String args[]) {
        int[] ints = {8,1,2,2,3};
        System.out.println(Arrays.toString(new L1365_ARRAY().smallerNumbersThanCurrent(ints)));
    }

    // public int[] smallerNumbersThanCurrent(int[] nums) {
    //     int[] temp = new int[nums.length];
    //     System.arraycopy(nums, 0, temp, 0, nums.length);
    //     Arrays.sort(temp);

    //     Map<Integer, Integer> map = new HashMap<>();
    //     map.put(temp[0], 0);
    //     for(int i = 1; i < nums.length; i++) {
    //         if(temp[i] == temp[i - 1]) continue;
    //         map.put(temp[i], i);
    //     }

    //     for(int i = 0; i < nums.length; i++) temp[i] = map.get(nums[i]);

    //     // map.forEach((k, v) -> {System.out.println(k + " -> " + v);});
    //     return temp;
    // }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int count[] = new int[101];
        for(int nm : nums) count[nm]++;

        int rank = 0;
        for(int i = 0; i < count.length; i++) {
            int temp = count[i];
            count[i] = rank;
            rank = rank + temp;
        }

        for(int i = 0; i < nums.length; i++) nums[i] = count[nums[i]];
        return nums; 
    }
}