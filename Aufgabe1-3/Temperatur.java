import java.util.Arrays;

public class Temperatur implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/lufttemperatur.html
    //           Normwerte: [0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f] Betrachtungszeitraum: 1981-2010 in °C
    //           Werte Jahr 2020: [1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f]
    //INV: monatlicheWerte.length == 12 & normWerte.length == 12 & faktor in [0.0,1.0]
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f};
    private float faktor;

    //VORB: mW.length == 12
    public Temperatur(float[] mW){
        monatlicheWerte = mW;
        Faktor();
    }

    //VORB: abweichungen.length == 12 & abweichungen[i] >= 0.5f
    //NACHB: simuliert das Vergehen eines Jahres
    //       dabei werden die monatlichen Werte neu errechnet aus abweichungen und den Normwerten
    //       anschließend wird der faktor aktualisiert
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
        Faktor();
    }

    //KOMMENTAR: abweichungsgrad legt fest ab wann eine Abweichung drastisch wird
    //NACHB: setzt faktor neu
    //       errechnet wie drastisch die diesjährigen Durchschnittswerte von den Normwerten abweichen
    private void Faktor(){
        float abweichungsgrad = 1.2f;
        float grenzwertAbw = 3.5f;
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
    //NACHB: gibt Auskunft darüber, wie sehr sich this Einfluss und ei in einem guten Verhältnis zueinander befinden
    //       bei einem idealen Verhältnis wird 1.0f zurückgegeben
    //       je schlechter das Verhältnis, umso näher bei 0.0f
    //       wenn das Verältnis zu stark vom idealen Verhältnis abweicht, wird 0.0f zurückgegeben
    //       gibt einen Wert in [0.0,1.0] zurück
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

    //NACHB: gibt den Faktor des Einflusses zurück
    public float getFaktor() {
        return faktor;
    }

    //NACHB: gibt das Array mit den monatlichen Werten zurück
    @Override
    public float[] getMonatlicheWerte() {
        return monatlicheWerte;
    }

    //NACHB: gibt den Inhalt des monatlicheWerte-Arrays als String zurück
    public String toString(){
        return Arrays.toString(monatlicheWerte);
    }

}
