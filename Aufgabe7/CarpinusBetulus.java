public class CarpinusBetulus implements ShadowTree {
    //INV: height > 0
    //     position[0] >= 0 & position[1] >= 0
    private float height;
    private int[] position = new int[2];

    //h > 0 & x >= 0 & y >= 0
    public CarpinusBetulus(Number h, int x, int y){
        height = h.floatValue();
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
        height += g.floatValue();
    }

    @Override
    public Number getLeavesOrHeight() {
        return height;
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
        return true;
    }

    @Override
    public boolean isLessSuitableThan(CarpinusBetulus c) {
        return c.height < this.height;
    }

    @Override
    public boolean isLessSuitableThan(Betula b) {
        return true;
    }

    @Override
    public boolean isLessSuitableThan(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "CarpinusBetulus ( " + "height: " + height + " )";
    }
}
//