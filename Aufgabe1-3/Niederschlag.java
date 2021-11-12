public class Niederschlag extends Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/niederschlag.html
    //           Normwerte: [37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f] Betrachtungszeitraum: 1981-2010 in mm Wasserhöhe
    //           Werte Jahr 2020: [19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f]
    //CLIENT-CONSTRAINT: Mure() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor Mure()
    //SCHLECHT: ERROR: Da Plus1Jahr() & Mure() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private static final float[] normW = new float[]{37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f};

    //VORB: mW.length == 12
    public Niederschlag(float[] mW){
        super(mW,normW);
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float Mure(){
        return Faktor(1.5f,3.0f);
    }
}
