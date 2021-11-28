public interface Group<X, Y> extends Iterable<X>, Relation<X, Y> {
    //KOMMENTAR: Group ist ein Interface mit den Obertypen Relation<X, Y> und Iterable<X>

    //VORB: e != null
    //NACHB: falls e noch nicht im Container vorhanden ist wird es hinzugefügt sonst nicht
    void add(X e);
}
