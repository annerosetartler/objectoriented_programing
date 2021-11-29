import java.util.Iterator;

public class SingleGroup<X> implements Group<X, X> {
    //KOMMENTAR: Singlegroup ist ein als Liste implementierter Container mit Einträgen vom Typ X,
    //           die "mit sich selbst verglichen werden". Auf den Vergleich wird hier allerdings verzichtet,
    //           da dieser immer true zurückgibt.
    //INV: size >= 0
    //     invoked >= 0
    private Node head = null;
    private Node tail = null;
    private int invoked;
    private int size;

    public SingleGroup() {
        invoked = 0;
        size = 0;
    }

    @Override
    //NACHB: fügt ein Element x in die Liste ein, falls die Liste noch kein zu x identisches Objekt besitzt
    //HISTORY-CONSTRAINT SERVER: size erhöht sich mit jedem Aufruf von add() um 1
    public void add(X x) {
        if (x == null) {
            return;
        }
        if (head == null) {
            tail = head = new Node(x);
        } else {
            for (X value : this) {
                if (value == x) {
                    return;
                }
            }
            tail = tail.next = new Node(x);
        }
        size ++;
    }

    @Override
    //NACHB: gibt die Anzahl der Elemente in der Liste zurück
    public int getSize() {
        return size;
    }

    public Iterator<X> iterator() {
        return new ListIter();
    }

    @Override
    //NACHB: gibt true zurück, wenn x und y identisch sind und weder x noch y null sind
    //HISTORY-CONSTRAINT SERVER: invoked erhöht sich mit jedem Aufruf von related() um 1
    public boolean related(X x, X y) {
        if (x == null || y == null) {
            return false;
        }
        invoked++;
        return x == y;
    }

    @Override
    public int invoked() {
        return invoked;
    }

    @Override
    public String toString() {
        return '{' + elemString() + '}';
    }

    //NACHB: gibt den Inhalt der Liste als String zurück
    //       z.B.: x, y, z
    private String elemString() {
        String s = "";
        if (head == null) {
            return s;
        }
        Node p = head;
        s += p.elem.toString();
        while (p.next != null) {
            p = p.next;
            s += ", " + p.elem.toString();
        }
        return s;
    }

    private class Node {
        //KOMMENTAR: ist ein Knoten in der Liste
        private X elem;
        private Node next = null;

        ///VORB: elem != null
        private Node(X elem) {
            this.elem = elem;
        }
    }

    private class ListIter implements Iterator<X> {
        private Node p = head;
        private Node last = null, prelast = null;

        //NACHB: gibt das nächste Element in der Liste zurück
        //       gibt null zurück, wenn die Liste leer ist
        public X next() {
            if (p == null) {
                return null;
            }
            X elem = p.elem;
            prelast = last;
            last = p;
            p = p.next;
            return elem;
        }

        public boolean hasNext() {
            return p != null;
        }

        //NACHB: entfernt das Element aus der Liste, das zuletzt von next() ausgegeben wurde
        //HISTORY-CONSTRAINT CLIENT: vor einem Aufruf von remove() muss zuvor next() aufgerufen werden
        //HISTORY-CONSTRAINT SERVER: size verringert sich mit jedem Aufruf von remove() um 1
        public void remove() {
            if (prelast == null) {
                head = p;
                last = null;
            } else if (p == null) {
                prelast.next = null;
                tail = prelast;
            } else {
                prelast.next = p;
                last = prelast;
            }
            size--;
        }
    }
}
