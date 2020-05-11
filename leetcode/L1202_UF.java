import java.util.*;

public class L1202_UF {
    public static void main(String args[]) {
        String s = "cba";
        int[][] arr = {{0,1},{1,2}};
        List<List<Integer>> list = new ArrayList<>();
        for(int[] a : arr) {
            List<Integer> temp = new ArrayList<>();
            for(int x : a) temp.add(x);
            list.add(temp);
        }

        System.out.println(new L1202_UF().smallestStringWithSwaps(s, list));
    }

    int parent[];
    int size[];

    public int find(int key) {
        if(key != parent[key]) parent[key] = find(parent[key]);
        return parent[key];
    }

    public void union(int key1, int key2) {
        int p1 = find(key1);
        int p2 = find(key2);
        
        if(p1 == p2) return;

        if(size[p1] > size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        parent = new int[s.length()];
        size = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for(List<Integer> pair : pairs) union(pair.get(0), pair.get(1));

        Map<Integer, LinkedList<Character>> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            int p = find(i);
            map.putIfAbsent(p, new LinkedList<>());
            map.get(p).add(s.charAt(i));
        }

        map.forEach((k, v) -> Collections.sort(v));

        char[] ans = new char[s.length()];
        for(int i = 0; i < s.length(); i++) {
            ans[i] = map.get(find(i)).remove();
        }

        return new String(ans);
    }
}