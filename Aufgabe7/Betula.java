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
    public String toString() {
        return "Betula ( " + "leaves: " + leaves + " )";
    }
}
