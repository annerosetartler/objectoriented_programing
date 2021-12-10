public class Quercus implements LightTree {
    //INV: leaves > 0
    //     position[0] >= 0 & position[1] >= 0
    private int leaves;
    private int[] position = new int[2];

    //h > 0 & x >= 0 & y >= 0
    public Quercus(Number l, int x, int y){
        leaves = l.intValue();
        position[0] = x;
        position[1] = y;
    }

    public int[] getPosition() {
        return position;
    }

    @Override
    public boolean hasSamePosition(int x, int y) {
        return position[0] == x && position[1] == y;
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
        return t.isInputLessSuitableThanThis(this);
    }

    @Override
    public boolean isInputLessSuitableThanThis(Tree t) {
        return t.isLessSuitableThan(this);
    }

    @Override
    public boolean isInputLessSuitableThanThis(Fagus f) {
        return true;
    }

    @Override
    public boolean isInputLessSuitableThanThis(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean isInputLessSuitableThanThis(Betula b) {
        return true;
    }

    @Override
    public boolean isInputLessSuitableThanThis(Quercus q) {
        return q.leaves < this.leaves;
    }

    @Override
    public String toString() {
        return "Quercus ( " + "leaves: " + leaves + " )";
    }
}
//