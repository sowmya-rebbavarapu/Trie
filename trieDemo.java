import java.util.*;
class TrieNode{
    TrieNode[] children=new TrieNode[26];
    boolean end;
}
class Trie{
    private final TrieNode root;
    public Trie(){
        root=new TrieNode();
    }
    public void insert(String word)
    {
        TrieNode curr=root;
        for(char ch:word.toCharArray())
        {
            int idx=ch-'a';
            if(curr.children[idx]==null)
            {
                curr.children[idx]=new TrieNode();
            }
            curr=curr.children[idx];
        }
        curr.end=true;
    }
    public boolean search(String word)
    {
        TrieNode curr=root;
        for(char ch:word.toCharArray())
        {
            int idx=ch-'a';
            if(curr.children[idx]==null)
            {
                return false;
            }
            curr=curr.children[idx];
        }
        return curr.end;
    }
    public List<String> getAllWords() {
        List<String> result = new ArrayList<>();
        dfs(root, new StringBuilder(), result);
        return result;
    }
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null)
                return result;
            current = current.children[index];
        }
        dfs(current, new StringBuilder(prefix), result);
        return result;
    }
    private void dfs(TrieNode node, StringBuilder path, List<String> result) {
        if (node.end)
            result.add(path.toString());
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int idx = ch - 'a';
            if (node.children[idx] != null) {
                path.append(ch);
                dfs(node.children[idx], path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

}
public class trieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Trie Menu ====");
            System.out.println("1. Insert a word");
            System.out.println("2. Search a word");
            System.out.println("3. Get all words");
            System.out.println("4. Get words with a prefix");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter word to insert: ");
                    String wordToInsert = scanner.nextLine();
                    trie.insert(wordToInsert);
                    System.out.println("Inserted successfully!");
                    break;
                case 2:
                    System.out.print("Enter word to search: ");
                    String wordToSearch = scanner.nextLine();
                    System.out.println("Found? " + trie.search(wordToSearch));
                    break;
                case 3:
                    System.out.println("All words in trie:");
                    for (String word : trie.getAllWords()) {
                        System.out.println(word);
                    }
                    break;
                case 4:
                    System.out.print("Enter prefix: ");
                    String prefix = scanner.nextLine();
                    List<String> wordsWithPrefix = trie.getWordsWithPrefix(prefix);
                    if (wordsWithPrefix.isEmpty()) {
                        System.out.println("No words found with that prefix.");
                    } else {
                        System.out.println("Words with prefix \"" + prefix + "\":");
                        for (String w : wordsWithPrefix) {
                            System.out.println(w);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
