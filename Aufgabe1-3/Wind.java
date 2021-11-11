public class Wind extends Einflüsse{
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/wind-monat.html
    //           Normwerte: [13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f] Betrachtungszeitraum: 1981-2010 in km/h
    //           Werte Jahr 2020: [10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f]
    //CLIENT-CONSTRAINT: Sturm() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor Sturm()
    //SCHLECHT: ERROR: Da Plus1Jahr() & Sturm() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private static final float[] normW = new float[]{13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f};

    //VORB: mW.length == 12
    public Wind(float[] mW){
        super(mW,normW);
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float Sturm(){
        return Faktor(1.15f,1.5f);
    }
}
