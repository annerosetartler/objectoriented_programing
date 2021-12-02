import java.util.Iterator;

public class List {
    //KOMMENTAR: List ist eine verkettete Liste
    //INV: size >= 0
    private Node head = null;
    private Node tail = null;
    private int size;

    public List() {
        size = 0;
    }

    //NACHB: fügt ein Element x ans Ende der Liste ein
    //HISTORY-CONSTRAINT SERVER: size erhöht sich mit jedem Aufruf von add() um 1
    public void add(Object x) {
        if (x == null) {
            return;
        }
        if (head == null) {
            tail = head = new Node(x);
        } else {
            tail = tail.next = new Node(x);
        }
        size ++;
    }

    //NACHB: gibt die Anzahl der Elemente in der Liste zurück
    public int getSize() {
        return size;
    }

    public Iterator iterator() {
        return new ListIter();
    }

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
        //INV: elem != null
        private Object elem;
        private Node next = null;

        ///VORB: elem != null
        private Node(Object elem) {
            this.elem = elem;
        }
    }

    private class ListIter implements Iterator {
        private Node p = head;
        private Node last = null, prelast = null;

        //NACHB: gibt das nächste Element in der Liste zurück
        //       gibt null zurück, wenn die Liste leer ist
        public Object next() {
            if (p == null) {
                return null;
            }
            Object elem = p.elem;
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
