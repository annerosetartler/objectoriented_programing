public class OpenArea implements Shade {
    public OpenArea(){}

    @Override
    public boolean isTreeCompatible(Fagus f) {
        return false;
    }

    @Override
    public boolean isTreeCompatible(CarpinusBetulus c) {
        return false;
    }

    @Override
    public boolean isTreeCompatible(Betula b) {
        return true;
    }

    @Override
    public boolean isTreeCompatible(Quercus q) {
        return true;
    }
}
