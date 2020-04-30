class L0208_TRIE {

    static class TrieNode {
        int wordCount;
        TrieNode[] childs;

        public TrieNode() {
            childs = new TrieNode[26];
        }
    }
    
    public TrieNode rootNode;

    public L0208_TRIE() {
        rootNode = new TrieNode();
    }
    
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode current = rootNode;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(current.childs[index] == null) current.childs[index] = new TrieNode();
            current = current.childs[index];
        }
        current.wordCount++;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = rootNode;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(current.childs[index] == null) return false;
            current = current.childs[index];
        }

        return current.wordCount > 0;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        TrieNode current = rootNode;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(current.childs[index] == null) return false;
            current = current.childs[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */