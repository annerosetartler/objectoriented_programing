public interface Shade {
    //Shade ist eine Art von Beschattung.

    //NACHB: gibt ein Objekt von OpenArea zurück
    Shade cut();

    //VORB: f != null
    //NACHB: gibt true zurück, wenn f unter dieser Beschattungsart wachsen kann
    boolean isTreeCompatible(Fagus f);

    //VORB: c != null
    //NACHB: gibt true zurück, wenn c unter dieser Beschattungsart wachsen kann
    boolean isTreeCompatible(CarpinusBetulus c);

    //VORB: b != null
    //NACHB: gibt true zurück, wenn b unter dieser Beschattungsart wachsen kann
    boolean isTreeCompatible(Betula b);

    //VORB: q != null
    //NACHB: gibt true zurück, wenn q unter dieser Beschattungsart wachsen kann
    boolean isTreeCompatible(Quercus q);

    //NACHB: gibt einen String zurück, der die Art der Beschattung ausgibt
    String toString();
}
