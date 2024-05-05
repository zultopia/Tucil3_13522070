import java.util.*;

public class GBFS {
    private Set<String> dictionary;

    public GBFS(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public Result findPath(String startWord, String endWord) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n ->
                Utility.heuristic(n.getWord(), endWord)));

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

            queue.clear(); 
            List<String> neighbors = Utility.getNeighbors(current.getWord(), dictionary);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, 0, current));
                }
            }
        }

        // No Solution
        return new Result(Collections.emptyList(), nodesVisited);
    }
}