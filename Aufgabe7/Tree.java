public interface Tree {
    //KOMMENTAR: Tree ist ein Jungbaum. Ein Jungbaum hat einen Standort und entsprechend der Art noch eine Wuchshöhe oder
    //           eine Anzahl an mit Licht versorgten Blättern.

    //NACHB: gibt den Standort des Baums zurück
    int[] getPosition();

    //NACHB: gibt true zurück, wenn die x-Koordinate von this Tree mit x übereinstimmt
    //       und die y-Koordinate von this Tree mit y übereinstimmt
    //       sonst wird false zurückgegeben
    boolean hasSamePosition(int x, int y);

    //VORB: g >= 0
    //NACHB: vergrößert die Anzahl der mit Licht versorgten Blätter oder die Wuchshöhe von this Tree um den Wert g
    void grow(Number g);

    //NACHB: gibt die Anzahl der mit Licht versorgten Blätter oder die Wuchshöhe von this Tree zurück
    Number getLeavesOrHeight();

    //NACHB: gibt ein Objekt vom Typ Shade zurück, welches von this Tree bestimmt wird
    Shade setShade();

    //VORB: s != null
    //NACHB: gibt true zurück, wenn this Tree mit Shade s kompatibel ist, sonst false
    boolean isShadeCompatible(Shade s);

    //VORB: t != null
    //NACHB: gibt true zurück wenn this Tree weniger geeignet ist als t, sonst false
    boolean isLessSuitableThan(Tree t);

    //VORB: t != null
    //NACHB: gibt true zurück, wenn t weniger geeignet ist als this Tree, sonst false
    boolean isInputLessSuitableThanThis(Tree t);

    //VORB: f != null
    //NACHB: gibt true zurück, wenn f weniger geeignet ist als this Tree, sonst false
    boolean isInputLessSuitableThanThis(Fagus f);

    //VORB: c != null
    //NACHB: gibt true zurück, wenn c weniger geeignet ist als this Tree, sonst false
    boolean isInputLessSuitableThanThis(CarpinusBetulus c);

    //VORB: b != null
    //NACHB: gibt true zurück, wenn b weniger geeignet ist als this Tree, sonst false
    boolean isInputLessSuitableThanThis(Betula b);

    //VORB: q != null
    //NACHB: gibt true zurück, wenn q weniger geeignet ist als this Tree, sonst false
    boolean isInputLessSuitableThanThis(Quercus q);

    //NACHB: gibt einen String zurück, der die Art des Jungbaums sowie dessen Anzahl an mit Licht versorgten Blättern
    //       oder Wuchshöhe ausgibt
    String toString();
}
