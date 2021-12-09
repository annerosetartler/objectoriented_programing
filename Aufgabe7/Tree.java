public interface Tree {

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //NACHB: gibt true zurück, wenn dieser Baum und t den gleichen Standort haben
    boolean haveSamePosition(Tree t);

    //VORB: g > 0
    void grow(Number g);

    Number getLeavesOrHeight();

    Shade setShade();

    boolean isShadeCompatible(Shade s);

    boolean eliminateThis(Tree t);

    boolean eliminateThis(Fagus f);
    boolean eliminateThis(CarpinusBetulus c);
    boolean eliminateThis(Betula b);
    boolean eliminateThis(Quercus q);

    String toString();
}
