public interface Relation<X, Y> {
    //KOMMENTAR: Relation ist ein generisches Interface

    //VORB: x & y != null
    boolean related(X x, Y y);

    //NACHB: gibt die Anzahl der bissherigen Aufrufe von related in diesem Objekt zurück //ToDo: Kommentar dazu, dass wir zwei Iteratoren f. MultiIterator-Klasse haben
    int invoked();
}
