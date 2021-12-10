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
    public Number getLeavesOrHeight() {
        return leaves;
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
    public boolean isLessSuitableThan(Tree t) {
        return t.isLessSuitableThan(this);
    }

    @Override
    public boolean isLessSuitableThan(Fagus f) {
        return false;
    }

    @Override
    public boolean isLessSuitableThan(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean isLessSuitableThan(Betula b) {
        return b.leaves < this.leaves;
    }

    @Override
    public boolean isLessSuitableThan(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "Betula ( " + "leaves: " + leaves + " )";
    }
}
//