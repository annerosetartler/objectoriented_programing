public class Sonne extends Einflüsse{
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/luftsonne.html
    //           Normwerte: [69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f] Betrachtungszeitraum: 1981-2010 in h
    //           Werte Jahr 2020: [69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f]
    //CLIENT-CONSTRAINT: SonneZuRegen() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor SonneZuRegen()
    //SCHLECHT: ERROR: Da Plus1Jahr() & SonneZuRegen() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private static final float[] normW = new float[]{69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f};

    //VORB: mW.length == 12
    public Sonne(float[] mW){
        super(mW,normW);
    }

    //VORB: e != null
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float SonneZuRegen(Einflüsse e){
        return VerhältnisZu(e,2.0f,6.0f);
    }
}
