import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start word: ");
        String startWord = scanner.next(); 
        System.out.print("Enter end word: ");
        String endWord = scanner.next(); 

        if (startWord.length() != endWord.length()) {
            System.out.println("Start word and end word must have the same length.");
            return; 
        }

        if (startWord.equalsIgnoreCase(endWord)) {  
            System.out.println("Start word and end word are the same: " + startWord);
            return; 
        }

        // int wordLength = startWord.length();
        // Set<String> dictionary = Dictionary.loadDictionary(wordLength);
        Set<String> dictionary = WordDictionary.loadDictionary(); 

        if (!dictionary.contains(startWord.toLowerCase()) || !dictionary.contains(endWord.toLowerCase())) {
            System.out.println("Start word and/or end word not in dictionary.");
            return; 
        }

        System.out.print("Choose algorithm (1-UCS, 2-GBFS, 3-A*): ");
        int algoChoice = scanner.nextInt();

        List<String> path = null;
        int nodesVisited = 0;
        long startTime = System.currentTimeMillis(); 
        
        switch (algoChoice) {
            case 1:
                UCS ucs = new UCS(dictionary);
                Result ucsResult = ucs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                path = ucsResult.getPath();
                nodesVisited = ucsResult.getNodesVisited();
                break;
            case 2:
                GBFS gbfs = new GBFS(dictionary);
                Result gbfsResult = gbfs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                path = gbfsResult.getPath();
                nodesVisited = gbfsResult.getNodesVisited();
                break;
            case 3:
                AStar astar = new AStar(dictionary);
                Result astarResult = astar.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                path = astarResult.getPath();
                nodesVisited = astarResult.getNodesVisited();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        long endTime = System.currentTimeMillis(); 
        long executionTime = endTime - startTime; 

        System.out.println("Nodes visited: " + nodesVisited);
        System.out.println("Execution time (ms): " + executionTime);

        if (path == null || path.isEmpty()) {
            System.out.println("No path solution found.");
            return; 
        }

        int steps = path.size() - 1; 

        System.out.println("Path:");
        for (int i = 0; i < path.size(); i++) {
            System.out.println((i + 1) + ". " + path.get(i)); 
        }

        System.out.println("Steps needed: " + steps); 
    }
}