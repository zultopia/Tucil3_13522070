import java.util.*;

public class UCS {
    private Set<String> dictionary;

    public UCS(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public Result findPath(String startWord, String endWord) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(startWord, 0, null));

        int nodesVisited = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();  

            if (visited.contains(current.getWord())) {
                continue; 
            }

            visited.add(current.getWord());  
            nodesVisited++;
            if (current.getWord().equals(endWord)) {
                List<String> path = new ArrayList<>();
                Node node = current;
                while (node != null) {
                    path.add(0, node.getWord());  
                    node = node.getParent();  
                }
                return new Result(path, nodesVisited);  
            }

            List<String> neighbors = Utility.getNeighbors(current.getWord(), dictionary);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) { 
                    queue.add(new Node(neighbor, current.getCost() + 1, current)); 
                }
            }
        }

        // Jika tidak ada jalur yang ditemukan
        return new Result(Collections.emptyList(), nodesVisited);
    }
}