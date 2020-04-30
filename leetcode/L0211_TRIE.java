import java.util.*;

class L0211_TRIE {

    static class TrieNode {
        int wordCount;
        TrieNode[] childs;

        public TrieNode() {
            childs = new TrieNode[26];
        }
    }
    
    
    public TrieNode rootNode;
    /** Initialize your data structure here. */
    public L0211_TRIE() {
        rootNode = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode current = rootNode;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(current.childs[index] == null) current.childs[index] = new TrieNode();
            current = current.childs[index];
        }
        current.wordCount++;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchRecursively(word, 0, rootNode);
    }

    public boolean searchRecursively(String word, int index, TrieNode root) {
        if(root == null) return false;
        if(index == word.length() && root.wordCount > 0) return true;
        if(index == word.length()) return false;
        
        char nextChar = word.charAt(index);
        boolean result = false;
        if(nextChar == '.') {
            for(int i = 0; i < 26; i++) {
                if(root.childs[i] != null && !result) 
                   result = result || searchRecursively(word, index + 1, root.childs[i]);
            }
        } else {
            result = searchRecursively(word, index + 1, root.childs[(char) (nextChar - 'a')]);
        }
        
        return result;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */