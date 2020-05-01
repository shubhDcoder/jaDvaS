import java.util.LinkedList;

public class TrieBasic {
    static final int R_ARY = 26;

    TrieNode root;
    int totalWords;

    public TrieBasic() {
        root = new TrieNode(R_ARY);
    }

    public void addWord(String word) {
        put(word, 0, root);
        totalWords++;
    }

    private TrieNode put(String word, int index, TrieNode current) {
        if(current == null) current = new TrieNode(R_ARY);
        if(index == word.length()) {
            current.wordCount++;
            return current;
        }
        int i = word.charAt(index) - 'a';
        current.childs[i] = put(word, index + 1, current.childs[i]); 
        return current;
    }

    public int get(String word) {
        TrieNode node = get(root, word, 0);
        if(node != null) return node.wordCount;
        else return -1;
    }

    private TrieNode get(TrieNode current, String word, int index) {
        if(current == null) return null;
        if(word.length() == index) return current;
        int ind = word.charAt(index) - 'a';
        return get(current.childs[ind], word, index + 1);
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        LinkedList<String> queue = new LinkedList<>();
        collect(get(root, prefix, 0), queue, prefix);
        return queue;
    }

    public void collect(TrieNode current, LinkedList<String> queue, String prefix) {
        if(current == null) return;
        if(current.wordCount > 0) queue.addLast(prefix);
        for(int i = 0; i < R_ARY; i++) {
            if(current.childs[i] != null) collect(current.childs[i], queue, prefix + (char)(i + 'a'));
        }
    }

    public Iterable<String> keysWithWildCards(String pattern) {
        LinkedList<String> queue = new LinkedList<>();
        collectWithWildCard(root, pattern, 0, queue, "");
        return queue;
    }

    private void collectWithWildCard(TrieNode current, String word, int index, LinkedList<String> queue, String ans) {
        if(current == null) return;
        if(index == word.length() && current.wordCount > 0) queue.addLast(ans);
        if(index == word.length()) return;
        char next = word.charAt(index);
        if(next == '.') {
            for(int i = 0; i < R_ARY; i++) {
                if(current.childs[i] != null) collectWithWildCard(current.childs[i], word, index + 1, queue, ans + (char) (i + 'a'));
            }
        } else {
            collectWithWildCard(current.childs[next - 'a'], word, index + 1, queue, ans + next);
        }
    }

    public String longestKeysWithPrefix(String prefix) {
        TrieNode node = get(root, prefix, 0);
        if(node == null) return null;
        else return longestKeysWithPrefix(node, prefix, "");
    }

    private String longestKeysWithPrefix(TrieNode node, String word, String ans) {
        if(node.wordCount > 0) ans = word;
        for(int i = 0; i < R_ARY; i++) {
            if(node.childs[i] != null) {
                String temp = longestKeysWithPrefix(node.childs[i], word + (char) (i + 'a'), ans);
                if(temp.length() > ans.length()) ans = temp;
            }
        }
        return ans;
    }

    public String longestKeyWithhWord(String word) {
        int length = longestKeysWithPrefix(root, word, 0, 0);
        return word.substring(0, length);
    }

    private int longestKeysWithPrefix(TrieNode node, String word, int index, int length) {
        if(node == null) return length;
        if(node.wordCount > 0) length = index;
        if(index == word.length()) return length;
        int c = word.charAt(index) - 'a';
        return longestKeysWithPrefix(node.childs[c], word, index + 1, length);
    }

    public void deleteWord(String word) {
        root = deleteWord(root, word, 0);
    } 

    private TrieNode deleteWord(TrieNode node, String word, int index) {
        if(index == word.length()) node.wordCount = 0;
        else {
            int c = word.charAt(index) - 'a';
            TrieNode temp = node.childs[c];
            if(temp != null)
                temp = deleteWord(temp, word, index + 1);        
        }

        if(node.wordCount > 0) return node;

        for(int i = 0; i < R_ARY; i++) {
            if(node.childs[i] != null) return node;
        }
        return null;
    }
}