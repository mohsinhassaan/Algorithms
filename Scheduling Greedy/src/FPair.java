class FPair implements Comparable<FPair> {
    public final Double x;
    public final Double y;

    public FPair(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(FPair other) {
        if (this.y < other.y)
            return -1;
        else if (other.y < this.y) {
            return 1;
        } else
            return this.x < other.x ? -1 : 1;
    }
}