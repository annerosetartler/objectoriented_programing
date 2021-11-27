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
    public String toString(){
        return '{' + list.toString() + a.toString() + '}';
    }

    @Override
    public Iterator<X> iterator() {
        return new MultiIter();
    }

    private class MultiIter implements Iterator<X> {
        private Iterator<X> iter;
        private Iterator<X> nextIter;
        private Iterator<X> nextFuncIter;
        boolean removed;
        private X currentElement;
        private X nextElement;
        private boolean newNext;

        public MultiIter(){
            iter = list.iterator();
            nextIter = list.iterator();
            nextFuncIter = list.iterator();
            removed = false;
            currentElement = null;
            nextElement = null;
            newNext = true;
        }

        @Override
        public boolean hasNext() {
            while (nextIter.hasNext() && !relationExists(nextElement)) {
                nextElement = nextIter.next();
            }

            if (relationExists(nextElement)){
                return true;
            }

            return false;
        }

        @Override
        public void remove() {
            nextFuncIter.remove();
        }

        @Override
        public X next() {
            X el = currentElement;

            while (iter.hasNext() && !relationExists(el)) {
                el = iter.next();
            }

            updateIterator(nextFuncIter, el);
            currentElement = iter.next();
            updateIterator(nextIter, currentElement);

            return el;
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

        private Iterator<X> updateIterator(Iterator<X> iterator, X el){
            X thisElement = iterator.next();

            while (thisElement != el){
                thisElement = iterator.next();
            }

            return iterator;
        }


    }
}
