import java.util.List;

public class Result {
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