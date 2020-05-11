import java.util.*;

public class L0212_TRIE {

    static class TrieNode {
        byte present;
        TrieNode[] childs;
        String word;
        TrieNode() {
            childs = new TrieNode[26];
        }
    }

    List<String> answer = new LinkedList<>();
    TrieNode root;

    public L0212_TRIE() {
        root = new TrieNode();
    }

    public static void main(String args[]) {

        char[][] board = {{'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}};

        String[] words = {"oath","pea","eat","rain"};


        new L0212_TRIE().findWords(board, words).forEach(System.out::println);
    }

    public byte[][] direction = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public List<String> findWords(char[][] board, String[] words) {
        if(board.length == 0 || board[0].length == 0 || words.length == 0) return answer;

        for(String word : words) put(word);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                String rep = String.valueOf(board[i][j]);
                if(get(rep)) {
                    populateAnswer(rep, board, i, j);
                }
            }
        }
        return answer;
    }
    
    public void populateAnswer(String c, char[][] board, int i, int j) {
        char backup = board[i][j];
        board[i][j] = '#';

        for(byte d = 0; d < direction.length; d++) {
            int newX = i + direction[d][0];
            int newY = j + direction[d][1];
            if(newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length && board[newX][newY] != '#') {
                String toCheck = c + board[newX][newY];
                if(get(toCheck)) {
                    populateAnswer(toCheck, board, newX, newY);
                }
            }
        }

        board[i][j] = backup;
    }

    // creation of TRIE
    public void put(String word) {
        put(root, word, 0);
    }

    private TrieNode put(TrieNode node, String word, int index) {
        if(node == null) node = new TrieNode();
        if(index == word.length()) {
            node.present = 1;
            node.word = word;
            return node;
        }
        int c = word.charAt(index) - 'a';
        node.childs[c] = put(node.childs[c], word, index + 1);
        return node;
    }

    public boolean get(String word) {
        return get(root, word, 0, answer);
    }

    private boolean get(TrieNode node, String word, int index, List<String> list) {
        if(node == null) return false;
        if(index == word.length()) {
            if(node.present == 1) list.add(node.word);
            return true;
        }
        int c = word.charAt(index) - 'a';
        return get(node.childs[c], word, index + 1, list);
    }
}