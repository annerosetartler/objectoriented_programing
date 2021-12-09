public interface Tree {

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //VORB: g > 0
    void grow(Number g);

    Shade setShade();

    boolean isShadeCompatible(Shade s);

    boolean eliminateThis(Tree t);

    boolean eliminateThis(Fagus f);
    boolean eliminateThis(CarpinusBetulus c);
    boolean eliminateThis(Betula b);
    boolean eliminateThis(Quercus q);

    String toString();
}
