import java.util.Arrays;

public class Wind implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/wind-monat.html
    //           Normwerte: [13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f] Betrachtungszeitraum: 1981-2010 in km/h
    //           Werte Jahr 2020: [10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f]
    //CLIENT-CONSTRAINT: Sturm() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor Sturm()
    //SCHLECHT: ERROR: Da Plus1Jahr() & Sturm() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f};

    //VORB: mW.length == 12
    public Wind(float[] mW){
        monatlicheWerte = mW;
    }

    //VORB: abweichungen.length == 12 & abweichungen[i] > 0.0f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float Faktor(){
        float abweichungsgrad = 1.15f;
        float grenzwertAbw = 1.5f;
        int zähler = 0;
        float sumAbw = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            float abweichung = monatlicheWerte[i]/normWerte[i];
            if(abweichung >= abweichungsgrad){
                zähler++;
                sumAbw += abweichung;
            }
        }
        if(sumAbw <= 0.0000001f){
            return 0.0f;
        }
        float totAbw = sumAbw/zähler;
        if(totAbw >= grenzwertAbw){
            return 1.0f;
        }else{
            return (totAbw - abweichungsgrad)/(grenzwertAbw - abweichungsgrad);
        }
    }

    @Override
    public float[] getMonatlicheWerte() {
        return monatlicheWerte;
    }

    public String toString(){
        return Arrays.toString(monatlicheWerte);
    }
}
