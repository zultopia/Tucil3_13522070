import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dapatkan input kata awal dan kata akhir
        System.out.print("Enter start word: ");
        String startWord = scanner.next();
        System.out.print("Enter end word: ");
        String endWord = scanner.next();

        if (startWord.length() != endWord.length()) {
            System.out.println("Start word and end word must be of the same length.");
            return;
        }

        // Muat kamus berdasarkan panjang kata
        int wordLength = startWord.length();
        Set<String> dictionary = loadDictionary(wordLength);

        if (!dictionary.contains(startWord.toLowerCase()) || !dictionary.contains(endWord.toLowerCase())) {
            System.out.println("Start word and/or end word not in dictionary.");
            return;
        }

        System.out.print("Choose algorithm (1-UCS, 2-GBFS, 3-A*): ");
        int algoChoice = scanner.nextInt();

        long startTime = System.currentTimeMillis();
        List<String> path = new ArrayList<>();
        int nodesVisited = 0;

        switch (algoChoice) {
            case 1:
                UCS ucs = new UCS(dictionary);
                UCS.Result ucsResult = ucs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                path = ucsResult.getPath();
                nodesVisited = ucsResult.getNodesVisited();
                break;
            case 2:
                GBFS gbfs = new GBFS(dictionary);
                GBFS.Result gbfsResult = gbfs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                path = gbfsResult.getPath();
                nodesVisited = gbfsResult.getNodesVisited();
                break;
            case 3:
                AStar astar = new AStar(dictionary);
                AStar.Result astarResult = astar.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                path = astarResult.getPath();
                nodesVisited = astarResult.getNodesVisited();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Path: " + path);
        System.out.println("Nodes visited: " + nodesVisited);
        System.out.println("Execution time (ms): " + executionTime);
    }

    private static Set<String> loadDictionary(int wordLength) {
        String filePath = "dictionary/dictionary-" + wordLength + "-letter-words.txt"; 
        Set<String> dictionary = new HashSet<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
        
        return dictionary;
    }
}