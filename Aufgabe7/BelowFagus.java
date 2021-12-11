public class BelowFagus implements Shade {
    //KOMMENTAR: BelowFagus ist eine Beschattungsart, unterhalb der sich ausschließlich Bäume der Art Fagus etablieren können.

    public BelowFagus(){}

    //NACHB: gibt ein Objekt von OpenArea zurück
    @Override
    public Shade cut() {
        return new OpenArea();
    }

    //VORB: f != null
    //NACHB: gibt true zurück, da sich Fagus unter BelowFagus etablieren kann
    @Override
    public boolean isTreeCompatible(Fagus f) {
        return true;
    }

    //VORB: c != null
    //NACHB: gibt false zurück, da sich CarpinusBetulus unter BelowFagus NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(CarpinusBetulus c) {
        return false;
    }

    //VORB: b != null
    //NACHB: gibt false zurück, da sich Betula unter BelowFagus NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(Betula b) {
        return false;
    }

    //VORB: q != null
    //NACHB: gibt false zurück, da sich Quercus unter BelowFagus NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(Quercus q) {
        return false;
    }

    @Override
    public String toString() {
        return "BelowFagus";
    }
}
