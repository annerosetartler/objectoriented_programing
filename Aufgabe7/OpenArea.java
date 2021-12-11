public class OpenArea implements Shade {
    //KOMMENTAR: OpenArea ist eine Beschattungsart, unterhalb der sich ausschließlich Lichtbaumarten (= Betula & Quercus)
    //           etablieren können. OpenArea bedeutet also "keine Beschattung".

    public OpenArea(){}

    //NACHB: gibt this zurück
    @Override
    public Shade cut() {
        return this;
    }

    //VORB: f != null
    //NACHB: gibt false zurück, da sich Fagus unter OpenArea NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(Fagus f) {
        return false;
    }

    //VORB: c != null
    //NACHB: gibt false zurück, da sich CarpinusBetulus unter OpenArea NICHT etablieren kann
    @Override
    public boolean isTreeCompatible(CarpinusBetulus c) {
        return false;
    }

    //VORB: b != null
    //NACHB: gibt true zurück, da sich Betula unter OpenArea etablieren kann
    @Override
    public boolean isTreeCompatible(Betula b) {
        return true;
    }

    //VORB: q != null
    //NACHB: gibt true zurück, da sich Quercus unter OpenArea etablieren kann
    @Override
    public boolean isTreeCompatible(Quercus q) {
        return true;
    }

    @Override
    public String toString() {
        return "OpenArea";
    }
}
