import java.util.*;

public class L0947_UF {
    public static void main(String args[]) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        // int[][] stones = {{0,0},{0,2},{1,1},{2,0},{2,2}};
        // int[][] stones = {{0,0}};
        // int[][] stones = {{0,1}, {1,0}};
        System.out.println(new L0947_UF().removeStones(stones));
    }


    Map<Integer, Integer> f = new HashMap<>();
    int islands = 0;

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; ++i) {
            System.out.println("doing union for " + stones[i][0] + " " + ~stones[i][1]);
            union(stones[i][0], ~stones[i][1]);
        }
        return stones.length - islands;
    }

    public int find(int x) {
        if (f.putIfAbsent(x, x) == null)
            islands++;
        if (x != f.get(x))
            f.put(x, find(f.get(x)));
        return f.get(x);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            f.put(x, y);
            islands--;
        }
    }

    // two for loops 26 ms

    /*
    int[] parent;
    int[] rank;
    int count;


    public int removeStones(int[][] stones) {
        int N = stones.length;
        parent = new int[N];
        rank = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        count = N;

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) union(i, j);
            }
        }

        return N - count;
    }

    public int find(int key) {
        if(key != parent[key]) parent[key] = find(parent[key]);
        return parent[key];
    }

    public void union(int key1, int key2) {
        int p1 = find(key1);
        int p2 = find(key2);
        if(p1 == p2) return;
        if(rank[p1] > rank[p2]) {
            parent[p2] = p1;
            rank[p1]++;
        } else {
            parent[p1] = p2;
            rank[p2]++;
        }
        count--;
    } */
}