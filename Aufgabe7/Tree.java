public interface Tree {

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //NACHB: gibt true zurück, wenn dieser Baum und t den gleichen Standort haben
    boolean hasSamePosition(int x, int y);

    //VORB: g > 0
    void grow(Number g);

    Number getLeavesOrHeight();

    Shade setShade();

    boolean isShadeCompatible(Shade s);

    //NACHB: gibt true zurück wenn dieser Baum (this) weniger geeignet ist als t
    boolean isLessSuitableThan(Tree t);

    //NACHB: gibt true zurück, wenn t weniger geeignet ist als dieser Baum(this)
    boolean isInputLessSuitableThanThis(Tree t);
    boolean isInputLessSuitableThanThis(Fagus f);
    boolean isInputLessSuitableThanThis(CarpinusBetulus c);
    boolean isInputLessSuitableThanThis(Betula b);
    boolean isInputLessSuitableThanThis(Quercus q);

    String toString();
}
//