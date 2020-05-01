public class EdgeWP {
    int toVertex;
    int weight;
    String path;

    public Edge(int toVertex, int weight, String path) {
        this.toVertex = toVertex;
        this.weight = weight;
        this.path = path;
    }

    @Override
    public String toString() {
        return String.format("[%2d @ %s %2d ]", toVertex, path, weight);
    }
}