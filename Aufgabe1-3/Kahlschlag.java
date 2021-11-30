public class Kahlschlag implements Bewirtschaftet {
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      Zähler >= 0 & <= 11
    private int Zähler;
    private float[] wirtschaftsfaktoren;

    //NACHB:
    public Kahlschlag() {
        wirtschaftsfaktoren = new float[4];
        Zähler = 0;
    }

    @Override
    //NACHB: gibt ein Array mit Faktoren zurück
    public float[] plusEinJahr(){
        if (Zähler == 0){
            wirtschaftsfaktoren[0] = 0;
            Zähler++;
        } else if (Zähler == 11){
            wirtschaftsfaktoren[0] = 1;
            Zähler = 0;
        }else{
            Zähler++;
        }
        return wirtschaftsfaktoren;
    }

}
