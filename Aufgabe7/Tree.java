public interface Tree {

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //NACHB: gibt true zurück, wenn dieser Baum und t den gleichen Standort haben
    boolean haveSamePosition(Tree t);

    //VORB: g > 0
    void grow(Number g);

    Shade setShade();

    boolean isShadeCompatible(Shade s);

    boolean eliminateThis(Tree t);

    boolean eliminateInput(Fagus f);
    boolean eliminateInput(CarpinusBetulus c);
    boolean eliminateInput(Betula b);
    boolean eliminateInput(Quercus q);

    String toString();
}
