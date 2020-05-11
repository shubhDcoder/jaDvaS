public class L0547_UF {
    public static void main(String args[]) {
        int[][] matrix = {{1,0,0},{0,1,0},{0,0,1}};
        // int[][] matrix = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(new L0547_UF().findCircleNum(matrix));
    }

    public int findCircleNum(int[][] matrix) {
        int N = matrix.length;
        DisjointSet uf = new DisjointSet(N);
        for(int i = 0; i < N; i++) {
            for(int j = i; j < N; j++) {
                if(matrix[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.count();
    }

    static class DisjointSet {
        int parent[];
        int count;

        public DisjointSet(int total) {
            parent = new int[total];  
            for(int i = 0; i < total; i++) parent[i] = i;          
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
            count++;
        }

        public int count() {
            return parent.length - count;
        }
    }
}