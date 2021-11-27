import java.util.Iterator;

public class MultiGroup<X, Y> implements Group<X, Y> {
    private SingleGroup<X> list;
    private Group<? extends Y, ?> a;
    private Relation<? super X, ? super Y> r;

    public MultiGroup(Group<? extends Y, ?> a, Relation<? super X, ? super Y> r) {
        list = new SingleGroup<X>();
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
        return '{' + list.toString() + "; " + a.toString() + '}';
    }

    @Override
    public Iterator<X> iterator() {
        return new MultiIter();
    }

    /*
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

     */
    private class MultiIter implements Iterator<X> {
        private Iterator<X> iter;
        private Iterator<X> nextIter;
        private X currentElement;
        private boolean keepSearching;

        public MultiIter(){
            iter = list.iterator();
            nextIter = list.iterator();
            keepSearching = true;
            currentElement = null;
        }

        @Override
        public boolean hasNext() {
            return checkNext();
        }

        @Override
        public void remove() {
            iter.remove();
        }

        @Override
        public X next() {
            while (iter.hasNext() && keepSearching) {
                currentElement = iter.next();
                if(relationExists(currentElement)){
                    keepSearching = false;
                }
            }
            keepSearching = true;
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

        private boolean checkNext() {
            boolean loop = true;
            boolean hasRelation = false;
            X elem = null;
            while(nextIter.hasNext() && loop){
                elem = nextIter.next();
                if(relationExists(elem)){
                    loop = false;
                    hasRelation = true;
                }
            }
            return hasRelation;
        }
    }
}
