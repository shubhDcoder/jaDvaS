public class L1319_UF {
    public static void main(String args[]) {
        // int n = 4; int[][] connections = {{0,1},{0,2},{1,2}};
        // int n = 6; int[][] connections = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        // int n = 6; int[][] connections = {{0,1},{0,2},{0,3},{1,2}};
        int n = 5; int[][] connections = {{0,1},{0,2},{3,4},{2,3}};

        System.out.println(new L1319_UF().makeConnected(n, connections));

    }

    int[] parent;
    int[] size;
    int count;
    int extraPairs;
    public int makeConnected(int n, int[][] connections) {
        parent = new int[n];
        size = new int[n];
        count = n;

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for(int[] arr : connections) {
            union(arr[0], arr[1]);
        }


        int ans = count - 1;
        return extraPairs >= ans ? ans : -1;
    }

    public int find(int key) {
        if(parent[key] != key) parent[key] = find(parent[key]);
        return parent[key];
    }

    public void union(int key1, int key2) {
        int p1 = find(key1);
        int p2 = find(key2);

        if(p1 == p2) {
            extraPairs++;
            return;
        }

        if(size[p1] > size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
        count--;
    }
}