public class Erholungsgebiet implements Bewirtschaftet  {
    //KOMMENTAR: Erholungsgebiet ist ein Untertyp von Bewirtschaftet und hat zusätzlich zum Array wirtschaftsfaktoren
    //           noch Variablen für die Anzahl der Wege und Hütten, sowie für neue Hütten und neue Wege in diesem Jahr
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0
    //      AnzahlWege >= 0
    //      AnzahlHütten >= 0
    //      neuH >= 0
    //      neuW >= 0
    private int AnzahlWege;
    private int AnzahlHütten;
    private int neuH;
    private int neuW;
    private float[] wirtschaftsfaktoren;

    public Erholungsgebiet() {
        wirtschaftsfaktoren = new float[4];
    }

    @Override
    //NACHB: gibt ein Array mit Faktoren zurück
    //       wenn AnzahlWege == 0 wird AnzahlWege und neuW inkrementiert
    //       wenn Math.random größer als 0.95 ist wird AnzahlWege und neuW inkrementiert
    //       sonst passiert nichts
    //       wenn Math.random größer als 0.98 ist wird AnzahlHütten und neuH inkrementiert
    //       sonst passiert nichts
    //       wirtschaftsfaktoren[3] wird an die neuen Werte in AnzahlWege, AnzahlHütten, neuH und neuW angepasst
    public float[] plusEinJahr() {
        neuH = 0;
        neuW = 0;
        if (AnzahlWege == 0){
            AnzahlWege++;
            neuW++;
        }else if (Math.random() > 0.95){
            AnzahlWege++;
            neuW++;
        }
        if (Math.random() > 0.98){
            AnzahlHütten++;
            neuH++;
        }

        wirtschaftsfaktoren[3] = neuH * 0.001f + neuW + 0.01f + AnzahlHütten * 0.0001f + AnzahlWege * 0.0001f;

        return wirtschaftsfaktoren;
    }

}
