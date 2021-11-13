public class Bewirtschaftet extends Bewirtschaftungsmodell {
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0

    //Vorb: altersKlassen > 0
    public Bewirtschaftet(int altersKlassen) {
        super(altersKlassen);
    }

    //ERROR: Methode wird nie aufgerufen. Also unnötig
    public float[] getWirtschaftsfaktoren() {
        return wirtschaftsfaktoren;
    }
}
