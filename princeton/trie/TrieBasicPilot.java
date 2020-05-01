import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TrieBasicPilot {
    public static void main(String args[]) throws FileNotFoundException {
        if(args.length == 0) throw new IllegalArgumentException("Please provide an input file");
        
        Scanner scanner = new Scanner(new File(args[0]));
        // constructing trie
        TrieBasic trie = new TrieBasic();
        while(scanner.hasNextLine()) trie.addWord(scanner.nextLine());
        System.out.println("Total Words :\t" + trie.totalWords);
        
        TrieBasicPilot pilot = new TrieBasicPilot();
        scanner = new Scanner(System.in);
        // pilot.showAllWords(trie);

        // pilot.showWordsWithPrefix(scanner, trie);

        // pilot.showWordsWithWildCard(scanner, trie);

        // pilot.longestKeysWIthPrefix(scanner, trie);

        // pilot.longestKeyWithhWord(scanner, trie);

        // deleteWords(trie);
        
        scanner.close();
    }

    public void deleteWords(TrieBasic trie) {
        showAllWords(trie);
        trie.deleteWord("badbaoy");
        showAllWords(trie);
    }

    public void showWordsWithWildCard(Scanner scanner, TrieBasic trie) {
        System.out.print("keyword you want to search with wildcard : \t");
        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if(word.equals("stop")) break;

            for(String key : trie.keysWithWildCards(word)) System.out.print(key + "\t");
            System.out.println();
            System.out.print("==> : \t");
        }

    }

    public void showAllWords(TrieBasic trie) {
        for(String key : trie.keys()) System.out.println(key);
        System.out.println();
        // for(String key : trie.keysWithPrefix("bas")) System.out.print(key + "\t");
        // System.out.println();
    }

    public void showWordsWithPrefix(Scanner scanner, TrieBasic trie) {
        System.out.print("keyword you want to search with a given prefix: \t");
        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if(word.equals("stop")) break;

            for(String key : trie.keysWithPrefix(word)) System.out.print(key + "\t");
            System.out.println();
            System.out.print("==> : \t");
        }
    }

    public void longestKeysWIthPrefix(Scanner scanner, TrieBasic trie) {
        System.out.println("longest word you want to search with prefix : ");
        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if(word.equals("stop")) break;
            System.out.println("longest word is : " + trie.longestKeysWithPrefix(word));
            System.out.print("==> : \t");
        }
    }

    public void longestKeyWithhWord(Scanner scanner, TrieBasic trie) {
        System.out.println("longest word in dict that matches yours : \t");
        System.out.print("==> : \t");
        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if(word.equals("stop")) break;
            System.out.println("longest word matched is : " + trie.longestKeyWithhWord(word));
            System.out.print("==> : \t");
        }
    }
}