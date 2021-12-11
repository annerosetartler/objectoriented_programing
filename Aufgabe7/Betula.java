public class Betula implements LightTree {
    //KOMMENTAR: Betula ist eine junge Birke und gehört zu den Lichtbaumarten.
    //           leaves ist die Anzahl der mit Licht versorgten Blätter und in position[] wird der Standort von this gespeichert

    //INV: leaves > 0
    //     position[0] >= 0 & position[1] >= 0
    private int leaves;
    private int[] position = new int[2];

    //VORB: h > 0 & x >= 0 & y >= 0
    public Betula(Number l, int x, int y){
        leaves = l.intValue();
        position[0] = x;
        position[1] = y;
    }

    //NACHB: gibt den Standort von this zurück
    @Override
    public int[] getPosition() {
        return position;
    }

    //NACHB: gibt true zurück, wenn die x-Koordinate von this mit x übereinstimmt
    //       und die y-Koordinate von this mit y übereinstimmt
    //       sonst wird false zurückgegeben
    @Override
    public boolean hasSamePosition(int x, int y) {
        return position[0] == x && position[1] == y;
    }

    //VORB: g >= 0
    //NACHB: vergrößert die Anzahl der mit Licht versorgten Blätter von this um den Wert g
    @Override
    public void grow(Number g) {
        leaves += g.intValue();
    }

    //NACHB: gibt die Anzahl der mit Licht versorgten Blätter von this zurück
    @Override
    public Number getLeavesOrHeight() {
        return leaves;
    }

    //NACHB: gibt ein Objekt von BelowNonFagus zurück, da dies der Beschattungsart einer Betula entspricht
    @Override
    public Shade setShade() {
        return new BelowNonFagus();
    }

    //VORB: s != null
    //NACHB: gibt true zurück, wenn this mit Shade s kompatibel ist, sonst false
    @Override
    public boolean isShadeCompatible(Shade s) {
        return s.isTreeCompatible(this);
    }

    //VORB: t != null
    //NACHB: gibt true zurück, wenn this weniger geeignet ist als t, sonst false
    @Override
    public boolean isLessSuitableThan(Tree t) {
        return t.isInputLessSuitableThanThis(this);
    }

    //VORB: t != null
    //NACHB: gibt true zurück, wenn t weniger geeignet ist als this, sonst false
    @Override
    public boolean isInputLessSuitableThanThis(Tree t) {
        return t.isLessSuitableThan(this);
    }

    //VORB: f != null
    //NACHB: gibt false zurück, da ein Fagus nicht weniger geeignet ist als eine Betula
    @Override
    public boolean isInputLessSuitableThanThis(Fagus f) {
        return false;
    }

    //VORB: c != null
    //NACHB: gibt false zurück, da CarpinusBetulus nicht weniger geeignet ist als Betula
    @Override
    public boolean isInputLessSuitableThanThis(CarpinusBetulus c) {
        return false;
    }

    //VORB: b != null
    //NACHB: gibt true zurück, wenn die Anzahl der mit Licht versorgten Blätter von b kleiner ist als jene von this, sonst false
    @Override
    public boolean isInputLessSuitableThanThis(Betula b) {
        return b.leaves < this.leaves;
    }

    //VORB: q != null
    //NACHB: gibt false zurück, da Quercus nicht weniger geeignet ist als Betula
    @Override
    public boolean isInputLessSuitableThanThis(Quercus q) {
        return false;
    }

    //NACHB: gibt einen String zurück, der die Art des Jungbaums (=Betula) sowie dessen Anzahl an mit Licht versorgten
    //       Blättern zurückgibt
    @Override
    public String toString() {
        return "Betula ( " + "leaves: " + leaves + " )";
    }
}
