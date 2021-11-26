import java.util.Iterator;

public class MultiGroup<X, Y> implements Group<X, Y> {
    private SingleGroup<X> list;
    private Group<Y, ?> a;
    private Relation<X, Y> r;

    public MultiGroup(Group<Y, ?> a, Relation<X, Y> r) {
        this.a = a;
        this.r = r;
    }

    @Override
    public void add(X e) {
        for (X elem : list) {
            if (e == elem) {
                return;
            }
        }
        list.add(e);
    }

    @Override
    public boolean related(X x, Y y) {
        return r.related(x, y);
    }

    @Override
    public int invoked() {
        return r.invoked() + a.invoked();
    }

    @Override
    public Iterator<X> iterator() {
        return new MultiIter();
    }

    private class MultiIter implements Iterator<X> {
        private Iterator<X> iter;
        private Iterator<X> nextIter;
        boolean removed;
        private X currentElement;
        private X nextElement;
        private boolean newNext;

        public MultiIter(){
            iter = list.iterator();
            nextIter = list.iterator();
            removed = false;
            currentElement = null;
            nextElement = null;
            newNext = true;
        }

        @Override
        public boolean hasNext() {
            if (!newNext){
                return nextIter.hasNext();
            }

            while (nextIter.hasNext() && !relationExists(nextElement)) {
                nextElement = nextIter.next();
            }

            newNext = false;

            return nextIter.hasNext();
        }

        @Override
        public void remove() {
            iter.remove();
        }

        @Override
        public X next() {
            while (iter.hasNext() && !relationExists(currentElement)) {
                currentElement = iter.next();
            }

            newNext = true;
            return currentElement;
        }

        private boolean relationExists(X x) {
            if (x == null) {
                return false;
            }
            for (Y y : a) {
                if (related(x, y)){
                    return true;
                }
            }

            return false;
        }


    }
}
