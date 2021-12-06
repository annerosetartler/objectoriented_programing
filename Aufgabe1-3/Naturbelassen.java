public class Naturbelassen implements Bewirtschaftungsmodell {
    //KOMMENTAR: Naturbelassen ist ein Modell und implementiert daher Bewirtschaftungsmodell
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    private float[] wirtschaftsfaktoren = new float[4];

    public Naturbelassen() {}

    @Override
    //NACHB: gibt ein Array mit Faktoren zurück
    public float[] plusEinJahr() {
        return wirtschaftsfaktoren;
    }
}
