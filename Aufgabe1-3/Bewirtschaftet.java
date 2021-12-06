public interface Bewirtschaftet extends Bewirtschaftungsmodell {
    //KOMMENTAR: Bewirtschaftet ist ein Interface und extended Bewirtschaftungsmodell
    //           Bewirtschaftet implementiert Modelle in welchen Menschen einfluss auf den Wald haben
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
}
