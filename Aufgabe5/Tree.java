public interface Tree {
    //KOMMENTAR: Tree ist ein Interface mit Bäumen als Instanzen.
    //           Tree hat eine Baumhöhe.
    //INV: height > 0

    //NACHB: gibt die Baumhöhe zurück
    Integer height();

    String toString();
}
