import java.util.Iterator;

public class MultiGroup<X, Y> implements Group<X, Y> {
    private Group<X, Y> a;
    private Relation<X, Y> r;

    public MultiGroup(Group<X, Y> a, Relation<X, Y> r) {
        this.a = a;
        this.r = r;
    }

    @Override
    public void add(X e) {
    }

    @Override
    public boolean related(X x, Y y) {
        return false;
    }

    @Override
    public int invoked() {
        return 0;
    }

    @Override
    public Iterator<X> iterator() {
        return null;
    }
}
