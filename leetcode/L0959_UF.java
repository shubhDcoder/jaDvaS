public class L0959_DFS {

    public static void main(String args[]) {

        // String[] input = {"\\ ","/ "};
        String[] input = {" /","  "};
        // String[] input = {"\\/","/\\"};
        // String[] input = {"/\\","\\/"};

        System.out.println(new L0959_DFS().regionsBySlashes(input));
    }

    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        DisjointSet uf = new DisjointSet(N * N * 4);

        for(int i = 0; i < N; i++) {
            for(int c = 0; c < N; c++) {
                char ch = grid[i].charAt(c);

                int root = 4 * (i * N + c);

                if(ch != '/') {
                    uf.union(root, root + 2);
                    uf.union(root + 1, root + 3);
                }
                if(ch != '\\') {
                    uf.union(root, root + 1);
                    uf.union(root + 2, root + 3);
                }

                if(c + 1 < N) {
                    int rootNext = 4 * (i * N + (c + 1));
                    uf.union(root + 2, rootNext + 1);
                }
                if(i + 1 < N) {
                    int rootNext = 4 * ((i + 1) * N + c);
                    uf.union(root + 3, rootNext);
                }
            }
        }

        return uf.count;
    }

    static class DisjointSet {
        int parent[];
        int count;

        public DisjointSet(int totalCount) {
            parent = new int[totalCount];
            count = totalCount;
            for(int i = 0; i < totalCount; i++) parent[i] = i;
        }

        public int find(int key) {
            if(key != parent[key]) parent[key] = find(parent[key]);
            return parent[key];
        }

        public void union(int key1, int key2) {
            int p1 = find(key1);
            int p2 = find(key2);
            if(p1 == p2) return;
            parent[p1] = p2;
            count--;
        }
    }
}