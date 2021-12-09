public interface Shade {
    Shade cut();

    boolean isTreeCompatible(Fagus f);
    boolean isTreeCompatible(CarpinusBetulus c);
    boolean isTreeCompatible(Betula b);
    boolean isTreeCompatible(Quercus q);

    String toString();
}
