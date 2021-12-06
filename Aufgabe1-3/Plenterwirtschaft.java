public class Plenterwirtschaft implements Bewirtschaftet {
    //KOMMENTAR: Plenterwirtschaft ist ein Untertyp von Bewirtschaftet
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0

    private float[] wirtschaftsfaktoren;

    public Plenterwirtschaft() {
        wirtschaftsfaktoren = new float[4];
    }

    @Override
    //NACHB: gibt ein Array mit Faktoren zurück
    public float[] plusEinJahr(){
        wirtschaftsfaktoren[1] = 1.0f;
        wirtschaftsfaktoren[2] = 0.45f;
        return wirtschaftsfaktoren;
    }
}
