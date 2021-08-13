package pl.sparkbit;

public abstract class Node implements Comparable<Node> {
    public final int frequency;

    protected Node(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node other){
        return this.frequency - other.frequency;
    }
}
