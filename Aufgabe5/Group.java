public interface Group<X, Y> extends Iterable<X>, Relation<X, Y> {
    void add(X e);
}
