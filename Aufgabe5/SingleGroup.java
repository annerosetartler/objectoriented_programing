import java.util.Iterator;

public class SingleGroup<X> implements Group<X, X> {
    private Node head = null;
    private Node tail = null;
    private int invoked;
    private int size;

    public SingleGroup() {
        invoked = 0;
        size = 0;
    }

    @Override
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
        size += 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    public Iterator<X> iterator() {
        return new ListIter();
    }

    @Override
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
        private X elem;
        private Node next = null;

        private Node(X elem) {
            this.elem = elem;
        }
    }

    private class ListIter implements Iterator<X> {
        private Node p = head;
        private Node last = null, prelast = null;

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
