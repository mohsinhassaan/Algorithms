public class Pair implements Comparable<Pair> {
    public final Integer x;
    public final Integer y;
    public final Integer weight;

    public Pair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.weight = 0;
    }

    public Pair(Integer x, Integer y, Integer weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair other) {
        if (this.y < other.y)
            return -1;
        else if (other.y < this.y) {
            return 1;
        } else
            return this.weight < other.weight ? -1 : 1;
    }
}