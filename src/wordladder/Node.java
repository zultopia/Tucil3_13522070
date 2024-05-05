import java.util.*;

public class Node {
    private String word;
    private int cost;
    private Node parent;
    private Set<String> visited; 

    public Node(String word, int cost, Node parent) {
        this.word = word;
        this.cost = cost;
        this.parent = parent;
        this.visited = new HashSet<>(); 
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

    public Set<String> getVisited() {
        return visited;
    }

    public void setVisited(Set<String> visited) {
        this.visited = visited;
    }
}