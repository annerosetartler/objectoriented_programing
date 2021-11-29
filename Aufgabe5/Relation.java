public interface Relation<X, Y> {
    //KOMMENTAR: Relation repräsentiert eine Beziehung zwischen zwei Typen

    //VORB: x != null & y != null
    boolean related(X x, Y y);

    //NACHB: gibt die Anzahl der bisherigen Aufrufe von related in diesem Objekt zurück
    int invoked();
}
