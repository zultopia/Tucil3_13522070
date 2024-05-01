import java.util.*;

public class GBFS {
    private Set<String> dictionary;

    public GBFS(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public Result findPath(String startWord, String endWord) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> heuristic(n.getWord(), endWord)));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(startWord, 0, null));

        int nodesVisited = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
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

            if (visited.contains(current.getWord())) {
                continue;
            }
            visited.add(current.getWord());

            for (String neighbor : getNeighbors(current.getWord())) {
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, current.getCost() + 1, current));
                }
            }
        }

        return new Result(Collections.emptyList(), nodesVisited);
    }

    private int heuristic(String word, String endWord) {
        int diff = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != endWord.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    private List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < wordArr.length; i++) {
            char originalChar = wordArr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    wordArr[i] = c;
                    String newWord = new String(wordArr);
                    if (dictionary.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            wordArr[i] = originalChar;
        }

        return neighbors;
    }

    private static class Node {
        private String word;
        private int cost;
        private Node parent;

        public Node(String word, int cost, Node parent) {
            this.word = word;
            this.cost = cost;
            this.parent = parent;
        }

        public String getWord() {
            return word;
        }

        public int getCost() {
            return cost;
        }

        public Node getParent() {
            return parent;
        }
    }

    public static class Result {
        private List<String> path;
        private int nodesVisited;

        public Result(List<String> path, int nodesVisited) {
            this.path = path;
            this.nodesVisited = nodesVisited;
        }

        public List<String> getPath() {
            return path;
        }

        public int getNodesVisited() {
            return nodesVisited;
        }
    }
}