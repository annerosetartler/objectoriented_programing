public class Temperatur extends Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/lufttemperatur.html
    //           Normwerte: [0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f] Betrachtungszeitraum: 1981-2010 in °C
    //           Werte Jahr 2020: [1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f]
    //CLIENT-CONSTRAINT: Hitze() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor Hitze()
    //SCHLECHT: ERROR: Da Plus1Jahr() & Hitze() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private static final float[] normW = new float[]{0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f};

    //VORB: mW.length == 12
    public Temperatur(float[] mW){
        super(mW,normW);
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float Hitze(){
        return Faktor(1.2f,3.5f);
    }
}
