import java.util.Iterator;

public class MultiGroup<X, Y> implements Group<X, Y> {
    //KOMMENTAR: Multigroup ist ein als Singlegroup implementierter Container, dessen Werte mit Werten aus a (vom Typ Group <? extends Y, ?>)
    //           verglichen werden

    private SingleGroup<X> list;
    private Group<? extends Y, ?> a;
    private Relation<? super X, ? super Y> r;

    //VORB: a != null & r != null
    public MultiGroup(Group<? extends Y, ?> a, Relation<? super X, ? super Y> r) {
        list = new SingleGroup<X>();
        this.a = a;
        this.r = r;
    }

    @Override
    //NACHB: fügt ein Element x in list ein, falls die Liste noch kein zu x identisches Objekt besitzt
    public void add(X e) {
        if (e == null) {
            return;
        }
        for (X elem : list) {
            if (e == elem) {
                return;
            }
        }
        list.add(e);
    }

    @Override
    //NACHB: gibt die Anzahl der Elemente zurück, die sowohl in list als auch in a enthalten sind
    public int getSize() {
        return list.getSize() + a.getSize();
    }

    @Override
    //VORB: x != null & y != null
    //NACHB: gibt true zurück, wenn x und y in r "related" sind
    //HISTORY-CONSTRAINT SERVER: invoked von r erhöht sich mit jedem Aufruf von related() um 1
    public boolean related(X x, Y y) {
        return r.related(x, y);
    }

    @Override
    //NACHB: gibt die Anzahl aller Aufrufe von related() in r zurück
    public int invoked() {
        return r.invoked() + a.invoked();
    }

    @Override
    //NACHB: gibt den Inhalt der Container (list und a) von Multigroup als String zurück
    public String toString() {
        return '{' + list.toString() + "; " + a.toString() + '}';
    }

    @Override
    public Iterator<X> iterator() {
        return new MultiIter();
    }

    private class MultiIter implements Iterator<X> {
        //KOMMENTAR: MultIter iteriert über die Einträge von list, vergleicht jeden Eintrag mit allen zurückgegebenen
        //          Einträgen aus a und gibt nur Einträge aus list zurück, die mit mindestens einem Eintrag aus a
        //          related sind
        //          invoked in r wird hier mehrfach erhöht, da zwei Iteratoren verwendet werden

        private Iterator<X> iter;
        private Iterator<X> nextIter;
        private X currentElement;
        private boolean keepSearching;

        private MultiIter() {
            iter = list.iterator();
            nextIter = list.iterator();
            keepSearching = true;
            currentElement = null;
        }

        @Override
        //NACHB: gibt true zurück falls es noch ein Element gibt, dass zurückgegeben werden kann
        public boolean hasNext() {
            return checkNext();
        }

        @Override
        //NACHB: entfernt das Element aus list, das zuletzt von next() ausgegeben wurde
        //HISTORY-CONSTRAINT CLIENT: vor einem Aufruf von remove() muss zuvor next() aufgerufen werden
        public void remove() {
            iter.remove();
        }

        @Override
        //NACHB: gibt das nächste Element aus list zurück, das mit mindestens einem Element aus a "related" ist
        //HISTORY-CONSTRAINT SERVER: invoked von r erhöht sich mit jedem Aufruf von next() mehrfach, da relationExists() aufgerufen wird
        public X next() {
            while (iter.hasNext() && keepSearching) {
                currentElement = iter.next();
                if (relationExists(currentElement)) {
                    keepSearching = false;
                }
            }
            keepSearching = true;
            return currentElement;
        }

        //NACHB: gibt true zurück wenn x mit mindestens einem Element aus a "related" ist
        //HISTORY-CONSTRAINT SERVER: invoked von r erhöht sich mit jedem Aufruf von relationExists() mehrfach, da related() aufgerufen wird
        private boolean relationExists(X x) {
            if (x == null) {
                return false;
            }
            for (Y y : a) {
                if (related(x, y)) {
                    return true;
                }
            }
            return false;
        }

        //NACHB: gibt true zurück wenn es noch ein Element in list gibt, das mit mindestens einem Element aus a "related" ist
        //HISTORY-CONSTRAINT SERVER: invoked von r erhöht sich mit jedem Aufruf von checkNext() mehrfach, da relationExists() aufgerufen wird
        private boolean checkNext() {
            boolean loop = true;
            boolean hasRelation = false;
            X elem = null;
            while (nextIter.hasNext() && loop) {
                elem = nextIter.next();
                if (relationExists(elem)) {
                    loop = false;
                    hasRelation = true;
                }
            }
            return hasRelation;
        }
    }
}
