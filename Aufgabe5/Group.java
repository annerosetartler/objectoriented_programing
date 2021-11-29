public interface Group<X, Y> extends Iterable<X>, Relation<X, Y> {
    //KOMMENTAR: Group ist ein Container mit einträgen vom Typ X, die mit Einträgen vom Typ Y eines anderen Containers
    //           verglichen werden.

    //VORB: e != null
    //NACHB: falls e noch nicht im Container vorhanden ist wird es hinzugefügt sonst nicht
    void add(X e);

    //NACHB: gibt die Anzahl aller Einträge des Containers zurück
    int getSize();
}
