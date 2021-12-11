public class BelowNonFagus implements Shade {
    //KOMMENTAR: BelowNonFagus ist eine Beschattungsart, unterhalb der sich ausschließlich Bäume der Arten Fagus &
    //           CarpinusBetulus etablieren können.

    public BelowNonFagus(){}

    //NACHB: gibt ein Objekt von OpenArea zurück
    @Override
    public Shade cut() {
        return new OpenArea();
    }

    //VORB: f != null
    //NACHB: gibt true zurück, da sich Fagus unter BelowNonFagus etablieren kann
    @Override
    public boolean isTreeCompatible(Fagus f) {
        return true;
    }

    //VORB: c != null
    //NACHB: gibt true zurück, da sich CarpinusBetulus unter BelowNonFagus etablieren kann
    @Override
    public boolean isTreeCompatible(CarpinusBetulus c) {
        return true;
    }

    //VORB: b != null
    //NACHB: gibt false zurück, da sich Betula unter BelowNonFagus NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(Betula b) {
        return false;
    }

    //VORB: q != null
    //NACHB: gibt false zurück, da sich Quercus unter BelowNonFagus NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "BelowNonFagus";
    }
}
