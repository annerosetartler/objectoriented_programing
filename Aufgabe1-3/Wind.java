import java.util.Arrays;

public class Wind implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/wind-monat.html
    //           Normwerte: [13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f] Betrachtungszeitraum: 1981-2010 in km/h
    //           Werte Jahr 2020: [10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f]
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f};
    private float faktor;

    //VORB: mW.length == 12
    public Wind(float[] mW){
        monatlicheWerte = mW;
        Faktor();
    }

    //VORB: abweichungen.length == 12 & abweichungen[i] > 0.0f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
        Faktor();
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    private void Faktor(){
        float abweichungsgrad = 1.15f;
        float grenzwertAbw = 1.5f;
        int zaehler = 0;
        float sumAbw = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            float abweichung = monatlicheWerte[i]/normWerte[i];
            if(abweichung >= abweichungsgrad){
                zaehler++;
                sumAbw += abweichung;
            }
        }
        if(sumAbw <= 0.0000001f){
            faktor = 0.0f;
        }else {
            float totAbw = sumAbw / zaehler;
            if (totAbw >= grenzwertAbw) {
                faktor = 1.0f;
            } else {
                faktor = (totAbw - abweichungsgrad) / (grenzwertAbw - abweichungsgrad);
            }
        }
    }

    //VORB: ei != null
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float VerhaeltnisZu(Einfluesse ei){
        float[] eiMw = ei.getMonatlicheWerte();
        float min = 2.0f;
        float max = 6.0f;
        float verhSum = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            verhSum += monatlicheWerte[i]/eiMw[i];
        }
        float durchschnittV = verhSum/12.0f;
        float mitte = (min + max)/2.0f;
        if(durchschnittV < min || durchschnittV > max){
            return 0.1f;
        }else if(durchschnittV >= min && durchschnittV < mitte){
            return (durchschnittV - min)/(mitte-min);
        }else if(durchschnittV == mitte){
            return 1.0f;
        }else{
            return 1.0f - (durchschnittV-mitte)/(max-mitte);
        }
    }

    public float getFaktor() {
        return faktor;
    }
    @Override
    public float[] getMonatlicheWerte() {
        return monatlicheWerte;
    }

    public String toString(){
        return Arrays.toString(monatlicheWerte);
    }
}
