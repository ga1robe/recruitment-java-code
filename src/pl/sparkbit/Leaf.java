package pl.sparkbit;

public class Leaf extends Node {
    public final char symbol;

    public Leaf(char symbol, int frequency) {
        super(frequency);
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return symbol * frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Leaf)) {
            return false;
        }
        Leaf other = (Leaf) obj;
        return other.frequency == frequency && other.symbol == symbol;
    }

    @Override
    public String toString() {
        return "symbol: " + symbol + ", frequency: " + frequency;
    }
}
