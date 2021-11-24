import java.util.Iterator;

public class MultiGroup<X, Y> implements Group<X, Y> {
    private SingleGroup<X> list;
    private Group<X, Y> a;
    private Relation<X, Y> r;
    private int invoked;

    public MultiGroup(Group<X, Y> a, Relation<X, Y> r) {
        this.a = a;
        this.r = r;
    }

    @Override
    public void add(X e) {

        //adden, wenn es weder hier noch in a ist
    }

    @Override
    public boolean related(X x, Y y) {
        return false;
    }

    @Override
    public int invoked() {
        return 0; // invoked + X.invoked();
    }

    @Override
    public Iterator<X> iterator() {
        return null;
    }

    private class MultiIter implements Iterator<Group>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }


}
