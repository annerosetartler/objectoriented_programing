import java.util.Iterator;

public class MultiGroup<X, Y> implements Group<X, Y> {
    private SingleGroup<X> list;
    private Group<X, Y> a;
    private Relation<X, Y> r;

    public MultiGroup(Group<X, Y> a, Relation<X, Y> r) {
        this.a = a;
        this.r = r;
    }

    @Override
    public void add(X e) {
        for(X elem : list){
           if(e == elem){
               return;
           }
        }
        list.add(e);
    }

    @Override
    public boolean related(X x, Y y) {
        return false;
    }

    @Override
    public int invoked() {
        return r.invoked() + a.invoked();
    }

    @Override
    public Iterator<X> iterator() {
        return new MultiIter();
    }

    private class MultiIter implements Iterator<X>{
        private Iterator<X> iter = list.iterator();
        private Iterator<X> delIter;
        boolean removed = false;

        @Override
        public boolean hasNext() {
            return iter.hasNext() || a.iterator().hasNext();
        }

        @Override
        public void remove() {
            if(!removed){
                delIter.remove();
                removed = true;
            }
        }

        @Override
        public X next() {
            while(iter.hasNext()){
                X next = iter.next();
                Iterator<X> iterA = a.iterator();
                while(iterA.hasNext()){
                    if(r.related(next, iterA.next())){
                        removed = false;
                        delIter = iter;
                        return next;
                    }
                }
            }
            return null;
        }
    }
}
