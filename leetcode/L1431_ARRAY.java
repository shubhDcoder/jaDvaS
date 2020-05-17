import java.util.*;

public class L1431_ARRAY {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = Integer.MIN_VALUE;
        for(int i = 0; i < candies.length; i++) {
            maxCandies = Math.max(candies[i], maxCandies);
        }

        List<Boolean> list = new ArrayList<>();
        for(int i = 0; i < candies.length; i++) {
            if(candies[i] + extraCandies >= maxCandies) list.add(true);
            else list.add(false);
        }
        return list;
    }
}