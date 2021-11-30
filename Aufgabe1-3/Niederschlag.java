import java.util.Arrays;

public class Niederschlag implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/niederschlag.html
    //           Normwerte: [37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f] Betrachtungszeitraum: 1981-2010 in mm Wasserhöhe
    //           Werte Jahr 2020: [19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f]
    //CLIENT-CONSTRAINT: Mure() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor Mure()
    //SCHLECHT: ERROR: Da Plus1Jahr() & Mure() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f};

    //VORB: mW.length == 12
    public Niederschlag(float[] mW){
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
        float abweichungsgrad = 1.5f;
        float grenzwertAbw = 3.0f;
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
