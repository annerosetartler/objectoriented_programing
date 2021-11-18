public interface Tree {
    //INV: size(= geschätzte Baumhöhe) > 0

    //NACHB: gibt den wissenschaftlichen (lateinischen) Namen der Baumart zurück
    String species();

    //NACHB: gibt die geschätzte Baumhöhe in Metern zurück
    float size();

    //VORB: size + change > 0
    //NACHB: ändert die geschätzte Höhe: wenn change > 0: Höhe wird vergrößert
    //                                   wenn change < 0: Höhe wird verringert
    void changeSize(float change);
}
