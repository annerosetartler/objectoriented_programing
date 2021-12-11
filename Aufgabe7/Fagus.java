public class Fagus implements ShadowTree {
    //KOMMENTAR: Fagus ist eine junge Buche und gehört zu den Schattenbaumarten.
    //           height ist die Wuchshöhe und in position[] wird der Standort von this gespeichert

    //INV: height > 0
    //     position[0] >= 0 & position[1] >= 0
    private float height;
    private int[] position = new int[2];

    //VORB: h > 0 & x >= 0 & y >= 0
    public Fagus(Number h, int x, int y){
        height = h.floatValue();
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
    //NACHB: vergrößert die Wuchshöhe von this um den Wert g
    @Override
    public void grow(Number g) {
        height += g.floatValue();
    }

    //NACHB: gibt die Wuchshöhe von this zurück
    @Override
    public Number getLeavesOrHeight() {
        return height;
    }

    //NACHB: gibt ein Objekt von BelowFagus zurück, da dies der Beschattungsart eines Fagus entspricht
    @Override
    public Shade setShade() {
        return new BelowFagus();
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
    //NACHB: gibt true zurück, wenn die Wuchshöhe von f kleiner ist als die Wuchshöhe von this, sonst false
    @Override
    public boolean isInputLessSuitableThanThis(Fagus f) {
        return f.height < this.height;
    }

    //VORB: c != null
    //NACHB: gibt false zurück, da CarpinusBetulus nicht weniger geeignet ist als Fagus
    @Override
    public boolean isInputLessSuitableThanThis(CarpinusBetulus c) {
        return false;
    }

    //VORB: b != null
    //NACHB: gibt false zurück, da Betula nicht weniger geeignet ist als Fagus
    @Override
    public boolean isInputLessSuitableThanThis(Betula b) {
        return false;
    }

    //VORB: q != null
    //NACHB: gibt false zurück, da Quercus nicht weniger geeignet ist als Fagus
    @Override
    public boolean isInputLessSuitableThanThis(Quercus q) {
        return false;
    }

    //NACHB: gibt einen String zurück, der die Art des Jungbaums (=Fagus) sowie dessen Wuchshöhe ausgibt
    @Override
    public String toString() {
        return "Fagus ( " + "height: " + height + " )";
    }
}
