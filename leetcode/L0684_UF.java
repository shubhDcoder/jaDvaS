import java.util.*;

public class L0684_UF {
    public static void main(String args[]) {
        // int[][] edges = {{1,2}, {1,3}, {2,3}};
        int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        System.out.println(Arrays.toString(new L0684_UF().findRedundantConnection(edges)));
    }

    int[] disjointSet;
    public int[] findRedundantConnection(int[][] edges) {
        disjointSet = new int[edges.length + 1];
        for(int i = 0; i < disjointSet.length; i++) disjointSet[i] = i;
        int[] ans = {};
        for(int[] row : edges) {
            byte r = union(row[0], row[1]);
            if(r == 0) return row;;
        }
        return ans;
    }

    int findParent(int key) {
        if(key != disjointSet[key]) disjointSet[key] = findParent(disjointSet[key]);
        return disjointSet[key];
    }

    byte union(int key1, int key2) {
        int p1 = findParent(key1);
        int p2 = findParent(key2);

        if(p1 == p2) return 0;

        disjointSet[p1] = p2;
        return 1;
    }
}