/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/word-azul")
public class Solver {

    @PostMapping("/find-path")
    public Map<String, Object> findPath(@RequestBody Map<String, String> request) {
        String startWord = request.get("startWord");
        String endWord = request.get("endWord");
        int algoChoice = Integer.parseInt(request.get("algoChoice"));

        // Muat kamus dan algoritma
        int wordLength = startWord.length();
        Set<String> dictionary = loadDictionary(wordLength);

        if (!dictionary.contains(startWord.toLowerCase()) || !dictionary.contains(endWord.toLowerCase())) {
            return Map.of("error", "Start word and/or end word not in dictionary.");
        }

        List<String> path = new ArrayList<>();
        int nodesVisited = 0;
        long startTime = System.currentTimeMillis();

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
                return Map.of("error", "Invalid algorithm choice.");
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        return Map.of(
            "path", path,
            "nodesVisited", nodesVisited,
            "executionTime", executionTime
        );
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
} */