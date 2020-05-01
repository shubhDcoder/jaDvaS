public class TrieNode {
    int wordCount;
    TrieNode[] childs;

    public TrieNode(int rAry) {
        childs = new TrieNode[rAry];
    }
}