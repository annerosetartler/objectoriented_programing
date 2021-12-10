public class Fagus implements ShadowTree {
    //INV: height > 0
    //     position[0] >= 0 & position[1] >= 0
    private float height;
    private int[] position = new int[2];

    //h > 0 & x >= 0 & y >= 0
    public Fagus(Number h, int x, int y){
        height = h.floatValue();
        position[0] = x;
        position[1] = y;
    }

    @Override
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
        height += g.floatValue();
    }

    @Override
    public Number getLeavesOrHeight() {
        return height;
    }

    @Override
    public Shade setShade() {
        return new BelowFagus();
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
        return f.height < this.height;
    }

    @Override
    public boolean isInputLessSuitableThanThis(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean isInputLessSuitableThanThis(Betula b) {
        return false;
    }

    @Override
    public boolean isInputLessSuitableThanThis(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "Fagus ( " + "height: " + height + " )";
    }
}
//