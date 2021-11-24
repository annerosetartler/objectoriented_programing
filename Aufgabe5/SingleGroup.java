import java.util.Iterator;

public class SingleGroup<X> implements Group<X, X> {

    @Override
    public void add(X e) {

    }

    @Override
    public boolean related(X x, X y) {
        return x == y;
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
