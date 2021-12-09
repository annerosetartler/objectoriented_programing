public class BelowFagus implements Shade {
    public BelowFagus(){}

    @Override
    public Shade cut() {
        return new OpenArea();
    }

    @Override
    public boolean isTreeCompatible(Fagus f) {
        return true;
    }

    @Override
    public boolean isTreeCompatible(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean isTreeCompatible(Betula b) {
        return false;
    }

    @Override
    public boolean isTreeCompatible(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "BelowFagus";
    }
}
