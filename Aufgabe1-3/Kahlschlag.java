public class Kahlschlag implements Bewirtschaftet {
    //KOMMENTAR: Kahlschlag ist ein Untertyp von Bewirtschafet zusätzlich zum Array wirtschaftsfaktoren
    //           hat Kahlschlag noch die Variable Zähler
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      Zähler >= 0 & <= 11
    private int Zähler;
    private float[] wirtschaftsfaktoren;

    public Kahlschlag() {
        wirtschaftsfaktoren = new float[4];
        Zähler = 0;
    }

    @Override
    //NACHB: gibt ein Array mit Faktoren zurück
    //       bei Zähler == 0 wird wirtschaftsfaktoren[0] gleich 0 gesetzt und der Zähler inkrementiert
    //       bei Zähler == 1 wird wirtschaftsfaktoren[0] gleich 1 gesetzt und der Zähler wird auf 0 gesetzt
    //       sonst wird der Zähler inkrementiert
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
