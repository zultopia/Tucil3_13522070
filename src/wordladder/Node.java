public class Node {
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