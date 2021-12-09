public class BelowFagus implements Shade {
    public BelowFagus(){}

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
}
