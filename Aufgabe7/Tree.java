public interface Tree {

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //VORB: g > 0
    void grow(Number g);

    Shade setShade();

    boolean isShadeCompatible(Shade s);

    String toString();
}
