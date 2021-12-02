import java.util.Arrays;

public class Niederschlag implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/niederschlag.html
    //           Normwerte: [37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f] Betrachtungszeitraum: 1981-2010 in mm Wasserhöhe
    //           Werte Jahr 2020: [19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f]
    //INV: monatlicheWerte.length == 12 & normWerte.length == 12 & faktor in [0.0,1.0]
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f};
    private float faktor;

    //VORB: mW.length == 12
    public Niederschlag(float[] mW){
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
    public void Faktor(){
        float abweichungsgrad = 1.5f;
        float grenzwertAbw = 3.0f;
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
