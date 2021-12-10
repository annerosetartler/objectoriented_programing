public interface Tree {

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //NACHB: gibt true zurück, wenn dieser Baum und t den gleichen Standort haben
    //TODO vergleich koords und nicht tree
    boolean haveSamePosition(Tree t);

    //VORB: g > 0
    void grow(Number g);

    Number getLeavesOrHeight();

    Shade setShade();

    boolean isShadeCompatible(Shade s);

    boolean isLessSuitableThan(Tree t);
    //TODO Methoden ergänzen um Zusicherungen nicht zu verletzten
    boolean isLessSuitableThan(Fagus f);
    boolean isLessSuitableThan(CarpinusBetulus c);
    boolean isLessSuitableThan(Betula b);
    boolean isLessSuitableThan(Quercus q);

    String toString();
}
//