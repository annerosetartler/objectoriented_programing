import java.util.Arrays;

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
    public boolean haveSamePosition(Tree t) {
        int[] tPos = t.getPosition();
        return position[0] == tPos[0] && position[1] == tPos[1];
    }

    //VORB: g > 0
    @Override
    public void grow(Number g) {
        height += g.floatValue();
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
    public boolean eliminateThis(Tree t) {
        return t.eliminateThis(this);
    }

    @Override
    public boolean eliminateThis(Fagus f) {
        return f.height < this.height;
    }

    @Override
    public boolean eliminateThis(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean eliminateThis(Betula b) {
        return false;
    }

    @Override
    public boolean eliminateThis(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "Fagus ( " + "height: " + height + " )";
    }
}
