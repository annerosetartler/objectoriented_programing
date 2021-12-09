public class Betula implements LightTree {
    //INV: leaves > 0
    //     position[0] >= 0 & position[1] >= 0
    private int leaves;
    private int[] position = new int[2];

    //h > 0 & x >= 0 & y >= 0
    public Betula(Number l, int x, int y){
        leaves = l.intValue();
        position[0] = x;
        position[1] = y;
    }

    public int[] getPosition() {
        return position;
    }

    @Override
    public boolean haveSamePosition(Tree t) {
        int[] tPos = t.getPosition();
        return position[0] == tPos[0] && position[1] == tPos[1];
    }

    //VORB: g > 0
    @Override
    public void grow(Number g) {
        leaves += g.intValue();
    }

    @Override
    public Shade setShade() {
        return new BelowNonFagus();
    }

    @Override
    public boolean isShadeCompatible(Shade s) {
        return s.isTreeCompatible(this);
    }

    @Override
    public boolean eliminateThis(Tree t) {
        return t.eliminateThis(this);
    }

    @Override
    public boolean eliminateThis(Fagus f) {
        return false;
    }

    @Override
    public boolean eliminateThis(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean eliminateThis(Betula b) {
        return b.leaves < this.leaves;
    }

    @Override
    public boolean eliminateThis(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "Betula ( " + "leaves: " + leaves + " )";
    }
}
