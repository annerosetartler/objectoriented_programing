import java.util.Arrays;

public class Temperatur implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/lufttemperatur.html
    //           Normwerte: [0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f] Betrachtungszeitraum: 1981-2010 in °C
    //           Werte Jahr 2020: [1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f]
    //CLIENT-CONSTRAINT: Hitze() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor Hitze()
    //SCHLECHT: ERROR: Da Plus1Jahr() & Hitze() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f};

    //VORB: mW.length == 12
    public Temperatur(float[] mW){
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
        float abweichungsgrad = 1.2f;
        float grenzwertAbw = 3.5f;
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
