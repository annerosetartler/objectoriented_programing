public class BelowNonFagus implements Shade {
    public BelowNonFagus(){}

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
        return true;
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
        return "BelowNonFagus";
    }
}
