public class L0990_UF {
    public static void main(String[] args) {
        String[] input = {"a==b","b!=a"};
        // String[] input = {"b==a","a==b"};
        // String[] input = {"a==b","b==c","a==c"};
        // String[] input = {"a==b","b!=c","c==a"};
        // String[] input = {"c==c","b==d","x!=z"};
        System.out.println(new L0990_UF().equationsPossible(input));
    }

    int parent[];
    int size[];
    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        size = new int[26];
        for(int i = 0; i < 26; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for(String str : equations) {
            if(str.charAt(1) == '=') union((int)(str.charAt(0) - 'a'), (int)(str.charAt(3) - 'a'));
        }

        for(String str : equations) {
            if(str.charAt(1) == '!' && (find((int)(str.charAt(0) - 'a')) == find((int)(str.charAt(3) - 'a')))) return false;
        }

        return true;
    }

    public int find(int key) {
        if(key != parent[key]) parent[key] = find(parent[key]);
        return parent[key];
    }

    public void union(int key1, int key2) {
        int p1 = find(key1);
        int p2 = find(key2);

        if(p1 != p2) {
            if(size[p1] > size[p2]) {
                parent[p2] = p1;
                size[p1] += size[p2];
            } else {
                parent[p1] = p2;
                size[p2] += size[p1];
            }
        }
    }
}