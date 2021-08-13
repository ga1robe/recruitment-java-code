package pl.sparkbit;

public class InnerNode extends Node {
    public final Node left;
    public final Node right;

    public InnerNode(Node left, Node right) {
        super(left.frequency + right.frequency);
        this.left = left;
        this.right = right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() + 123 * right.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InnerNode)) {
            return false;
        }
        InnerNode other = (InnerNode) obj;
        return other.left.equals(left) && other.right.equals(right);
    }

    @Override
    public String toString() {
        return "frequency: " + frequency + "\nleft: (" + left + ")\nright: (" + right +")";
    }
}
