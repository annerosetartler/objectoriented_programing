import java.util.Arrays;

public class Sonne implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/luftsonne.html
    //           Normwerte: [69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f] Betrachtungszeitraum: 1981-2010 in h
    //           Werte Jahr 2020: [69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f]
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f};
    private float faktor;

    //VORB: mW.length == 12
    public Sonne(float[] mW){
        monatlicheWerte = mW;
        Faktor();
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    private void Faktor(){
        float abweichungsgrad = 1.5f;
        float grenzwertAbw = 2.0f;
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

    //VORB: abweichungen.length == 12 & abweichungen[i] > 0.0f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
        Faktor();
    }

    //NACHB: gibt das Array mit den monatlichen Werten zurück
    public float[] getMonatlicheWerte(){
        return monatlicheWerte;
    }

    @Override
    public float getFaktor() {
        return faktor;
    }

    public String toString(){
        return Arrays.toString(monatlicheWerte);
    }
}
